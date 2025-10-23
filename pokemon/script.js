const poke_container = document.getElementById('poke-container');
const prevBtn = document.getElementById('prev-btn');
const nextBtn = document.getElementById('next-btn');
const pageTabs = document.getElementById('page-tabs');

const colors = {
    fire: '#FDDFDF', grass: '#DEFDE0', electric: '#FCF7DE', water: '#DEF3FD',
    ground: '#f4e7da', rock: '#d5d5d4', fairy: '#fceaff', poison: '#98d7a5',
    bug: '#f8d5a3', dragon: '#97b3e6', psychic: '#eaeda1', flying: '#F5F5F5',
    fighting: '#E6E0D4', normal: '#F5F5F5'
};
const main_types = Object.keys(colors);

let apiUrl = 'https://pokeapi.co/api/v2/pokemon?offset=0&limit=20';
let nextUrl = null;
let prevUrl = null;

// Fetch página de Pokémon
function fetchPokemons(url) {
    poke_container.innerHTML = 'Cargando...';
    fetch(url)
        .then(res => res.json())
        .then(data => {
            poke_container.innerHTML = '';
            nextUrl = data.next;
            prevUrl = data.previous;

            prevBtn.disabled = !prevUrl;
            nextBtn.disabled = !nextUrl;

            data.results.forEach(poke => {
                fetch(poke.url)
                    .then(res => res.json())
                    .then(pokemonData => createPokemonCard(pokemonData))
                    .catch(err => console.error(err));
            });

            // Crear pestañas de página simples
            pageTabs.innerHTML = '';
            const totalPages = Math.ceil(150 / 20);
            for(let i=1; i<=totalPages; i++){
                const btn = document.createElement('button');
                btn.classList.add('btn', 'btn-outline-primary', 'mx-1');
                btn.textContent = i;
                btn.addEventListener('click', () => {
                    const offset = (i - 1) * 20;
                    fetchPokemons(`https://pokeapi.co/api/v2/pokemon?offset=${offset}&limit=20`);
                });
                pageTabs.appendChild(btn);
            }
        })
        .catch(err => console.error(err));
}

// Crear card Pokémon
function createPokemonCard(pokemon){
    const pokemonEl = document.createElement('div');
    pokemonEl.classList.add('col-6', 'col-md-4', 'col-lg-3', 'pokemon-card');

    const name = pokemon.name[0].toUpperCase() + pokemon.name.slice(1);
    const id = pokemon.id.toString().padStart(3, '0');
    const types = pokemon.types.map(type => type.type.name);
    const type = main_types.find(t => types.indexOf(t) > -1);
    const color = colors[type];

    const cardHTML = `
        <div class="card text-center shadow" style="background-color:${color}; margin-bottom:20px;">
            <img src="${pokemon.sprites.front_default}" class="card-img-top" alt="${pokemon.name}">
            <div class="card-body">
                <h5 class="card-title">${name}</h5>
                <p class="card-text">Tipo: ${type}</p>
                <span class="text-muted">#${id}</span>
            </div>
        </div>
    `;
    pokemonEl.innerHTML = cardHTML;
    poke_container.appendChild(pokemonEl);
}

// Botones Next / Previous
nextBtn.addEventListener('click', () => {
    if(nextUrl) fetchPokemons(nextUrl);
});
prevBtn.addEventListener('click', () => {
    if(prevUrl) fetchPokemons(prevUrl);
});

// Cargar primera página
fetchPokemons(apiUrl);