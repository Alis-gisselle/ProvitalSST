/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
const CACHE_NAME = 'provitalsst-v1';
const ASSETS_TO_CACHE = [
  './',
  './login',
  './css/styles.css', // Cambia por tus archivos CSS reales
  './js/main.js'       // Cambia por tus JS locales
];

// Instalación e inicio de caché
self.addEventListener('install', (e) => {
  e.waitUntil(
    caches.open(CACHE_NAME).then((cache) => {
      return cache.addAll(ASSETS_TO_CACHE);
    })
  );
});

// Interceptar peticiones para servir desde el caché
self.addEventListener('fetch', (e) => {
  e.respondWith(
    caches.match(e.request).then((response) => {
      return response || fetch(e.request);
    })
  );
});

