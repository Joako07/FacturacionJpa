/*Poblar tabla clientes*/
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Andres','Guzman','profesor@hotamil.com','2017-08-28');
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Jhon','Moe','JhonAlto@hotamil.com','2008-07-27')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Joquin','Torvald','JoakoTor@gmail.com','2010-02-13')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Guillermo','Quiroga','Gile22@hotamil.com','2005-05-05')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Franco','Duarte','Franquito@libe.com.ar','2000-08-14')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Juanito','Ninse','Monada@hotamil.com','2020-07-27')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Laura','Noriega','LAura0004@hotamil.com','2020-11-27')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Terry','Dog','TerryDog@hotamil.com','2020-03-05')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Veronica','Teen','Vero67@hotamil.com','2020-08-17')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Luis','Taxi','TaxiLuz@hotamil.com','2020-01-02')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Cazandra','Stalon','Cazare@gamil.com','2020-03-27')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Cecilia','Dutto','DuttoCeci@gmail.com','2008-04-28')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Sapbe','Lokita','SabpeL@hotamil.com','2008-02-14')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Jorge','Rial','Rial14@hotamil.com','2008-09-22')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Andres','Calamaro','AndresCalam@hotamil.com','2008-12-27')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Rodrigo','Malo','rodriiM@hotamil.com','2001-09-11')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Rodrigo','Bueno','Elpotro@hotamil.com','2022-02-27')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Mike','Mouse','Wuau@yhaoo.com','2022-11-07')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Carlos','Rufino','RufinoCarlosJimenez@hotamil.com','2022-12-05')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Jhon','Moe','JhonBajo@hotamil.com','2022-06-13')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Esteban','Yriarte','Yriarte789@hotamil.com','2022-04-03')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Juan','Bario','JhonBario@hotamil.com','2022-12-11')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Roxana','Bustos','Roxi@hotamil.com','2022-12-03')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Miguel','Abuelo','Miguel1@hotamil.com','2022-12-07')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Federico','Rivera','RiveraF62@hotamil.com','2021-07-14')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Magallanes','Who','MagWhopo@hotamil.com','2022-06-13')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Geouge','Constanza','Georiuo@hotamil.com','1989-06-13')
INSERT INTO clientes(nombre,apellido,email, create_at) VALUES('Ross','Geller','Fiends@hotamil.com','1994-06-13')

/*Poblar tabla productos*/
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD','259990', NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara Digital','123490', NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Audriculares ZX31AP','4500', NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple Ipod shuffle','1449990', NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280','69990', NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro26','70000', NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones','30000', NOW());

/*Creamos facturas*/
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, now());
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES (1, 1, 1);
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES (2, 1, 4);
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES (1, 1, 5);
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES (7, 1, 7);

INSERT INTO facturas(descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', null, 3, now());
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES (3, 2, 6);

INSERT INTO facturas(descripcion, observacion, cliente_id, create_at) VALUES('Compra del Día', null, 1, now());
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES (2, 3, 4);