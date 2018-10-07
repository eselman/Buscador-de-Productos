# Buscador-de-Productos
Este proyecto consiste en una pequeña app para un buscador de productos usando una REST API publica.

# Arquitectura
La implementación hace uso del patrón MVP (Model View Presenter), a través del cual, el presenter se encarga de comunicarse con la capa de servicios y el modelo para obtener toda la información que necesitan las vistas.
Se utilizaron algunas librerias externas, las cuales son:
Retrofit: Encargada de simplificar el manejo de las llamadas a la REST API.
Gson: Para la Serializacion de los datos que se obtienen en las respuestas del servicio al modelo de la applicacion.
Picasso: Para la carga de imagenes de los productos.
