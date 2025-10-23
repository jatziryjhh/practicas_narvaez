function validarNumero(num, callback) {
  if (isNaN(num)) {
    callback("Debes ingresar un número válido.");
  } else if (!Number.isInteger(num)) {
    callback("Solo se permiten números enteros.");
  } else if (num < 1) {
    callback("No se permiten números negativos ni cero.");
  } else if (num > 25) {
    callback("El número máximo permitido es 25.");
  } else {
    callback(null, num);
  }
}

function generarTabla(num, callback) {
  let tabla = [];
  for (let i = 1; i <= num; i++) {
    tabla.push(`${num} x ${i} = ${num * i}`);
  }
  callback(tabla);
}

function mostrarResultado(tabla, callback) {
  let contenedor = document.getElementById("resultado");
  contenedor.innerHTML = "";
  tabla.forEach(linea => {
    let p = document.createElement("p");
    p.textContent = linea;
    contenedor.appendChild(p);
  });
  callback();
}

document.getElementById("btnCalcular").addEventListener("click", function() {
  let numero = parseFloat(document.getElementById("numero").value);

  validarNumero(numero, function(error, numValido) {
    let contenedor = document.getElementById("resultado");
    if (error) {
      contenedor.innerHTML = `<p style="color:red;">${error}</p>`;
      return;
    }
    generarTabla(numValido, function(tabla) {
            mostrarResultado(tabla, function() {
        console.log("Tabla mostrada con éxito");
      });

    });

  });
});