# Sistema de Tienda 🛒

El Sistema de Tienda es una aplicación Java que permite a los usuarios interactuar con una tienda, incluyendo funcionalidades para listar productos, agregarlos a un carrito de compras, y realizar pagos.

## Características ✨

- **Listado de Productos:** Los usuarios pueden ver todos los productos disponibles en la tienda.
- **Carrito de Compras:** Los usuarios pueden iniciar un carrito de compras, agregar productos a este, y ver los productos que han agregado.
- **Pago:** Los usuarios pueden realizar el pago de los productos agregados al carrito, eligiendo entre diferentes métodos de pago.
- **Creación de Facturas:** Al finalizar la compra, el sistema genera automáticamente una factura para el usuario.

## Cómo usar 📖

### Iniciar la aplicación

Para iniciar la aplicación, ejecute el método `main` en la clase `Main`. Esto cargará los datos de productos y clientes, y mostrará el menú principal.

### Navegar en el menú

El sistema utiliza un menú basado en consola para la navegación:

- Seleccione `1` para listar todos los productos disponibles.
- Seleccione `2` para iniciar el carrito de compras.
- Seleccione `0` para finalizar la aplicación.

### Agregar productos al carrito

Una vez iniciado el carrito de compras:

- Puede listar todos los productos y seleccionarlos para agregarlos a su carrito.
- Para agregar un producto, se le pedirá que ingrese el código o nombre del producto y la cantidad deseada.

### Realizar pago

Cuando esté listo para realizar el pago:

- Puede elegir entre realizar un pago en efectivo o con tarjeta de débito/crédito.
- Si elige pago en efectivo, se le pedirá que ingrese el monto con el que desea pagar.
- Al seleccionar el pago con tarjeta, el pago se registrará automáticamente.

## Desarrollo 🚀

Este sistema fue desarrollado utilizando Java y Swing para la interfaz de usuario (en caso de optar por una versión visual). Utiliza la biblioteca `javafaker` para generar datos ficticios para la demostración.

## Contribuir 🤝

1. **Fork el repositorio** 🍴
   - Hacé un fork del proyecto a tu cuenta de GitHub presionando el botón de fork en la parte superior derecha de esta página.

2. **Creá una rama para tus cambios** 🌱
   - `git checkout -b mi-nueva-caracteristica`

3. **Hacé tus cambios y el commit** 💻
   - `git add .`
   - `git commit -m "Añadiendo una nueva característica"`

4. **Push a tu fork y enviá una pull request** 🔁
   - `git push origin mi-nueva-caracteristica`
   - Luego, ve a tu fork en GitHub y haz clic en "Pull request" y enviá tus cambios a revisión.

---

Por favor, sentite libre de sugerir cualquier mejora o reportar bugs. ¡Toda contribución es bienvenida! 🌟
