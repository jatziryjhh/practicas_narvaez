const CACHE_NAME = 'cocktail-app-v1';
const ASSETS = [
    './',
    './index.html',
    './main.js',
    './assets/data/offline.json',
    './assets/images/coctel.png',
    './assets/images/dino.gif',
    'https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css',
    'https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.min.js'
];

// 🔹 INSTALACIÓN
self.addEventListener('install', event => {
    console.log('Service Worker: Instalando...');
    event.waitUntil(
        caches.open(CACHE_NAME).then(cache => cache.addAll(ASSETS))
    );
    self.skipWaiting();
});

// 🔹 ACTIVACIÓN
self.addEventListener('activate', event => {
    console.log('Service Worker: Activado');
    event.waitUntil(
        caches.keys().then(keys =>
            Promise.all(
                keys.filter(k => k !== CACHE_NAME).map(k => caches.delete(k))
            )
        )
    );
    self.clients.claim();
});

// 🔹 FETCH
self.addEventListener('fetch', event => {
    const request = event.request;
    const url = new URL(request.url);

    // API de cócteles
    if (url.hostname === 'www.thecocktaildb.com') {
        event.respondWith(
            fetch(request)
                .then(response => {
                    return caches.open(CACHE_NAME).then(cache => {
                        cache.put(request, response.clone());
                        return response;
                    });
                })
                .catch(() => caches.match('./assets/data/offline.json'))
        );
        return;
    }

    // Imágenes offline
    if (request.destination === 'image') {
        event.respondWith(
            fetch(request).catch(() => caches.match('./assets/images/dino.gif'))
        );
        return;
    }

    // HTML y otros
    event.respondWith(
        fetch(request)
            .then(response => {
                caches.open(CACHE_NAME).then(cache => cache.put(request, response.clone()));
                return response;
            })
            .catch(() => caches.match(request))
    );
});