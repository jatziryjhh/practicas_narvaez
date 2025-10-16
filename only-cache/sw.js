const CACHE_NAME = "app-shell-v1";
const ASSETS_APP_SHELL = [
  "/",
  "/index.html",
  "/pages/acercade.html",
  "/pages/ofertas.html",
  "/main.js",
];

self.addEventListener("install", (event) => {
  event.waitUntil(
    caches.open(CACHE_NAME).then((cache) => {
      return cache.addAll(ASSETS_APP_SHELL);
    })
  );
});               

self.addEventListener('fetch',event=>{
  console.log('Petición para:', event.request.url);
  event.respondWith(
    caches.match(event.request).then((response)=>{
      return response;
    })
  )
});
