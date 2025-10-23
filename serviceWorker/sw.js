// Detectar todas las peticiones fetch
var version = 'v1.0.3';

self.addEventListener('fetch', event => {
    console.log(`[${version}] Fetch detectado:`, event.request.url);
});