if ('serviceWorker' in navigator) {
  window.addEventListener('load', () => {
    navigator.serviceWorker
      .register('./sw.js')
      .then(reg => console.log('SW registrado con scope:', reg.scope))
      .catch(err => console.error('Error al registrar SW:', err));
  });
}

// Esperamos a que el DOM cargue
document.addEventListener("DOMContentLoaded", () => {
  const input = document.getElementById('searchInput');
  const btn = document.getElementById('searchBtn');
  const results = document.getElementById('results');

  btn.addEventListener('click', buscar);
  input.addEventListener('keypress', e => {
    if (e.key === 'Enter') buscar();
  });

  async function buscar() {
    const termino = input.value.trim();
    if (!termino) return;

    const url = `https://www.thecocktaildb.com/api/json/v1/1/search.php?s=${termino}`;

    try {
      const res = await fetch(url);
      const data = await res.json();
      mostrarResultados(data.drinks);
    } catch (error) {
      console.warn('Error de red, usando offline.json');
      const res = await fetch('./assets/data/offline.json');
      const data = await res.json();
      const filtrados = data.drinks.filter(d =>
        d.strDrink.toLowerCase().includes(termino.toLowerCase())
      );
      mostrarResultados(filtrados);
    }
  }

  function mostrarResultados(drinks) {
    results.innerHTML = '';

    if (!drinks || drinks.length === 0) {
      results.innerHTML = `
        <div class="text-center">
          <img src="./assets/images/dino.gif" alt="Sin conexión" class="img-fluid mb-3" style="max-width:200px;">
          <h2>No se encontraron cócteles</h2>
        </div>`;
      return;
    }

    drinks.forEach(drink => {
      const card = `
        <div class="col-12 col-md-4">
          <div class="card h-100 shadow-sm text-center">
            <img src="${drink.strDrinkThumb}" class="card-img-top" alt="${drink.strDrink}">
            <div class="card-body">
              <h5 class="card-title">${drink.strDrink}</h5>
            </div>
          </div>
        </div>
      `;
      results.innerHTML += card;
    });
  }
});