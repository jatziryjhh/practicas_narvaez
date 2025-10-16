const CACHE_NAME = "cocktail-app-v1";

const urlsToCache = [
  "/",
  "/index.html",
  "/main.js",
  "/manifest.json",
  "/assets/images/coctel.jpg",
  "/assets/data/offline.json",
  "https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css",
  "https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.min.js",
];

// 🔹 INSTALACIÓN: guardar en caché los archivos esenciales
self.addEventListener("install", (event) => {
  console.log("Service Worker: Instalando...");
  event.waitUntil(
    caches.open(CACHE_NAME).then((cache) => {
      console.log("Cacheando archivos iniciales...");
      return cache.addAll(urlsToCache).catch((err) => {
        console.warn("Algunos recursos no se pudieron cachear:", err);
      });
    })
  );
});

// 🔹 ACTIVACIÓN: limpiar cachés viejos
self.addEventListener("activate", (event) => {
  console.log("Service Worker: Activado");
  event.waitUntil(
    caches.keys().then((keys) =>
      Promise.all(
        keys.map((key) => {
          if (key !== CACHE_NAME) {
            console.log("Eliminando caché vieja:", key);
            return caches.delete(key);
          }
        })
      )
    )
  );
});

// 🔹 FETCH: responder peticiones de red o caché
self.addEventListener("fetch", (event) => {
  const request = event.request;
  const url = new URL(request.url);
  console.log("Petición para:", url.href);

  // 1️⃣ Si es la API de cócteles
  if (url.hostname === "www.thecocktaildb.com") {
    event.respondWith(
      fetch(request)
        .then((response) => {
          return caches.open(CACHE_NAME).then((cache) => {
            cache.put(request, response.clone());
            return response;
          });
        })
        .catch(() => {
          console.warn("⚠️ Sin conexión, usando offline.json");
          return caches.match("/assets/data/offline.json").then((offlineResp) => {
            if (offlineResp) return offlineResp;
            return new Response(
              JSON.stringify({
                drinks: [
                  { strDrink: "Sin conexión", strDrinkThumb: "/assets/images/coctel.jpg" },
                ],
              }),
              { headers: { "Content-Type": "application/json" } }
            );
          });
        })
    );
    return;
  }

  // 2️⃣ Imagen offline
  if (url.pathname.endsWith("/coctel.jpg")) {
    event.respondWith(caches.match("/assets/images/coctel.jpg"));
    return;
  }

  // 3️⃣ Otros recursos: network first, fallback cache
  event.respondWith(
    fetch(request)
      .then((response) => {
        caches.open(CACHE_NAME).then((cache) => cache.put(request, response.clone()));
        return response;
      })
      .catch(() => {
        return caches.match(request).then((res) => {
          if (res) return res;
          if (request.destination === "image") {
            return caches.match("/assets/images/coctel.jpg");
          }
        });
      })
  );
});
