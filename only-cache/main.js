if ("serviceWorker" in navigator) {
  window.addEventListener("load", () => {
    navigator.serviceWorker
      .register("/sw.js")
      .then((registration) => {
        console.log("SW registrado");
      })
      .catch((error) => {
        console.log("SW registro falló", error);
      });
  });
}