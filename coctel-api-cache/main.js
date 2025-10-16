// 🔹 Registro del Service Worker
if ("serviceWorker" in navigator) {
  window.addEventListener("load", () => {
    navigator.serviceWorker
      .register("/sw.js")
      .then(() => console.log("SW registrado"))
      .catch((error) => console.log("SW registro falló", error));
  });
}

// 🔹 Elementos del DOM
const input = document.getElementById('searchInput');
const btn = document.getElementById('searchBtn');
const results = document.getElementById('results');

// 🔹 Eventos
btn.addEventListener('click', buscar);
input.addEventListener('keypress', e => {
  if (e.key === 'Enter') buscar();
});

// 🔹 Función principal de búsqueda
async function buscar() {
  const termino = input.value.trim();
  if (!termino) return;

  const url = `https://www.thecocktaildb.com/api/json/v1/1/search.php?s=${termino}`;

  try {
    // Intentamos la búsqueda online
    const res = await fetch(url);
    const data = await res.json();
    mostrarResultados(data.drinks);
  } catch (error) {
    console.log('Error de red, usando offline.json');

    // 🔹 Intentar usar el cache del SW
    const cached = await caches.match('/assets/data/offline.json');
    if (cached) {
      const data = await cached.json();
      const filtrados = data.drinks.filter(d =>
        d.strDrink.toLowerCase().includes(termino.toLowerCase())
      );
      mostrarResultados(filtrados);
      return;
    }

    // 🔹 Fallback mínimo si ni cache tiene datos
    mostrarResultados([
      { strDrink: 'Sin conexión', strDrinkThumb: '/assets/images/coctel.jpg' }
    ]);
  }
}

// 🔹 Función para mostrar resultados
function mostrarResultados(drinks) {
  results.innerHTML = '';

  if (!drinks || drinks.length === 0) {
    results.innerHTML = `<p class="text-center text-muted">No se encontraron resultados</p>`;
    return;
  }

  drinks.forEach(drink => {
    const card = `
      <div class="col">
        <div class="card h-100 shadow-sm">
          <img src="${drink.strDrinkThumb}" class="card-img-top" alt="${drink.strDrink}">
          <div class="card-body text-center">
            <h6 class="card-title">${drink.strDrink}</h6>
          </div>
        </div>
      </div>
    `;
    results.innerHTML += card;
  });
}
