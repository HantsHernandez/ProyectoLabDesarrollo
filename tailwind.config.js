/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/**/*.{html,js}'],
  theme: {
    extend: {
      colors: {
        nuevo: '#046C4E',
        eliminar: '#EE4443',
        modificar: '#EAB206',
        leer: '#21417F',
      },
    },
  },
  plugins: [],
};
