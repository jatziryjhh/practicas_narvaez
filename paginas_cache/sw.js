const CACHE_NAME = "pwa-pages-v2";
const FILES_TO_CACHE = [
  "index.html",
  "page1.html",
  "page2.html",
  "style.css",
  "pageOffline.html",
  "manifest.json",
];

self.addEventListener("install", (e) => {
  e.waitUntil(
    caches.open(CACHE_NAME).then((cache) => {
      console.log("Archivos cacheados ✨");
      return cache.addAll(FILES_TO_CACHE);
    })
  );
  self.skipWaiting();
});

self.addEventListener("activate", (e) => {
  e.waitUntil(
    caches.keys().then((keys) =>
      Promise.all(
        keys.map((key) => {
          if (key !== CACHE_NAME) {
            console.log("Cache antiguo eliminado:", key);
            return caches.delete(key);
          }
        })
      )
    )
  );
  self.clients.claim();
});

self.addEventListener("fetch", (e) => {
  e.respondWith(
    caches.match(e.request).then((cachedResponse) => {
      if (cachedResponse) {
        // Si está en caché, sirve inmediatamente
        return cachedResponse;
      }
      // Si no está en caché, intenta la red
      return fetch(e.request).catch(() => {
        // Si falla la red, sirve página offline
        return caches.match("pageOffline.html");
      });
    })
  );
});
