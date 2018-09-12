INSERT INTO bdcomer.marca
(idmarca, dscmarca)
VALUES(1, 'PILSEN');
INSERT INTO bdcomer.marca
(idmarca, dscmarca)
VALUES(2, 'CRISTAL');
INSERT INTO bdcomer.marca
(idmarca, dscmarca)
VALUES(3, 'COCACOLA');
INSERT INTO bdcomer.marca
(idmarca, dscmarca)
VALUES(4, 'INCA KOLA');


INSERT INTO bdcomer.unidadmedida
(idunidadmedida, dscunidadmedida, abrunidadmedida, factor)
VALUES(1, 'UNIDAD', 'UND', 1);
INSERT INTO bdcomer.unidadmedida
(idunidadmedida, dscunidadmedida, abrunidadmedida, factor)
VALUES(2, 'LITROS', 'LT', 1);

INSERT INTO bdcomer.categoria
(idcategoria, dsccategoria)
VALUES(1, 'CERVEZAS');
INSERT INTO bdcomer.categoria
(idcategoria, dsccategoria)
VALUES(2, 'GASEOSAS');


INSERT INTO bdcomer.producto
(idproducto, dscproducto, dscproductocorto, idunidadmedida, idmarca, idmodelo, idlaboratorio, idcategoria, peso, estado, idmoneda, stockminimo, activo, exigevencimiento, exigelote, numeropiezas, id_catalogo_producto_qaliwarma, porcentaje_igv, porcentaje_isc, precio1_valor_venta, precio2_valor_venta, precio3_valor_venta, valor_igv_precio1, valor_igv_precio2, valor_igv_precio3, valor_isc_precio1, valor_isc_precio2, valor_isc_precio3, precio1, precio2, precio3)
VALUES(1, 'PILSEN 325ML', 'PILSEN BOTELLITA', 1, 1, NULL, NULL, 1, NULL, NULL, NULL, 10.000, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5.00, 4.00, 4.50);
INSERT INTO bdcomer.producto
(idproducto, dscproducto, dscproductocorto, idunidadmedida, idmarca, idmodelo, idlaboratorio, idcategoria, peso, estado, idmoneda, stockminimo, activo, exigevencimiento, exigelote, numeropiezas, id_catalogo_producto_qaliwarma, porcentaje_igv, porcentaje_isc, precio1_valor_venta, precio2_valor_venta, precio3_valor_venta, valor_igv_precio1, valor_igv_precio2, valor_igv_precio3, valor_isc_precio1, valor_isc_precio2, valor_isc_precio3, precio1, precio2, precio3)
VALUES(2, 'PILSEN SEIXPACK 325 ML', 'PISLN SIX', 1, 1, NULL, NULL, 1, NULL, NULL, NULL, 10.000, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 32.00, 31.00, 30.00);
INSERT INTO bdcomer.producto
(idproducto, dscproducto, dscproductocorto, idunidadmedida, idmarca, idmodelo, idlaboratorio, idcategoria, peso, estado, idmoneda, stockminimo, activo, exigevencimiento, exigelote, numeropiezas, id_catalogo_producto_qaliwarma, porcentaje_igv, porcentaje_isc, precio1_valor_venta, precio2_valor_venta, precio3_valor_venta, valor_igv_precio1, valor_igv_precio2, valor_igv_precio3, valor_isc_precio1, valor_isc_precio2, valor_isc_precio3, precio1, precio2, precio3)
VALUES(3, 'PILSEN 625ML', 'PILSEN BOTELLA', 1, 1, NULL, NULL, 1, NULL, NULL, NULL, 15.000, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.00, 5.00, 4.50);
INSERT INTO bdcomer.producto
(idproducto, dscproducto, dscproductocorto, idunidadmedida, idmarca, idmodelo, idlaboratorio, idcategoria, peso, estado, idmoneda, stockminimo, activo, exigevencimiento, exigelote, numeropiezas, id_catalogo_producto_qaliwarma, porcentaje_igv, porcentaje_isc, precio1_valor_venta, precio2_valor_venta, precio3_valor_venta, valor_igv_precio1, valor_igv_precio2, valor_igv_precio3, valor_isc_precio1, valor_isc_precio2, valor_isc_precio3, precio1, precio2, precio3)
VALUES(4, 'PILSEN CAJA 12  625ML', 'PILSEN JONCA', 1, 1, NULL, NULL, 1, NULL, NULL, NULL, 30.000, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 45.00, 44.00, 42.00);
INSERT INTO bdcomer.producto
(idproducto, dscproducto, dscproductocorto, idunidadmedida, idmarca, idmodelo, idlaboratorio, idcategoria, peso, estado, idmoneda, stockminimo, activo, exigevencimiento, exigelote, numeropiezas, id_catalogo_producto_qaliwarma, porcentaje_igv, porcentaje_isc, precio1_valor_venta, precio2_valor_venta, precio3_valor_venta, valor_igv_precio1, valor_igv_precio2, valor_igv_precio3, valor_isc_precio1, valor_isc_precio2, valor_isc_precio3, precio1, precio2, precio3)
VALUES(5, 'PILSEN EDICION ESPECIAL 625ML', 'PILSEN ESPECIAL', 1, 1, NULL, NULL, 1, NULL, NULL, NULL, 15.000, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8.00, 7.00, 73.00);
INSERT INTO bdcomer.producto
(idproducto, dscproducto, dscproductocorto, idunidadmedida, idmarca, idmodelo, idlaboratorio, idcategoria, peso, estado, idmoneda, stockminimo, activo, exigevencimiento, exigelote, numeropiezas, id_catalogo_producto_qaliwarma, porcentaje_igv, porcentaje_isc, precio1_valor_venta, precio2_valor_venta, precio3_valor_venta, valor_igv_precio1, valor_igv_precio2, valor_igv_precio3, valor_isc_precio1, valor_isc_precio2, valor_isc_precio3, precio1, precio2, precio3)
VALUES(6, 'FANTA 3LT', 'FA', 2, 3, NULL, NULL, 2, NULL, NULL, NULL, 5.000, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.00, 5.00, 4.00);
INSERT INTO bdcomer.producto
(idproducto, dscproducto, dscproductocorto, idunidadmedida, idmarca, idmodelo, idlaboratorio, idcategoria, peso, estado, idmoneda, stockminimo, activo, exigevencimiento, exigelote, numeropiezas, id_catalogo_producto_qaliwarma, porcentaje_igv, porcentaje_isc, precio1_valor_venta, precio2_valor_venta, precio3_valor_venta, valor_igv_precio1, valor_igv_precio2, valor_igv_precio3, valor_isc_precio1, valor_isc_precio2, valor_isc_precio3, precio1, precio2, precio3)
VALUES(7, 'CORONA 325ml', 'CORONA', 1, 4, NULL, NULL, 1, NULL, NULL, NULL, 5.000, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7.00, 7.50, 6.00);


INSERT INTO bdcomer.stockactual
(idstockactual, idproducto, idalmacen, stockactual)
VALUES('1', 1, 2, 100.000);
INSERT INTO bdcomer.stockactual
(idstockactual, idproducto, idalmacen, stockactual)
VALUES('2', 2, 2, 50.000);
INSERT INTO bdcomer.stockactual
(idstockactual, idproducto, idalmacen, stockactual)
VALUES('3', 3, 2, 30.000);
INSERT INTO bdcomer.stockactual
(idstockactual, idproducto, idalmacen, stockactual)
VALUES('4', 1, 1, 50.000);
INSERT INTO bdcomer.stockactual
(idstockactual, idproducto, idalmacen, stockactual)
VALUES('5', 2, 1, 30.000);
INSERT INTO bdcomer.stockactual
(idstockactual, idproducto, idalmacen, stockactual)
VALUES('6', 3, 1, 20.000);
INSERT INTO bdcomer.stockactual
(idstockactual, idproducto, idalmacen, stockactual)
VALUES('7', 4, 1, 10.000);
INSERT INTO bdcomer.stockactual
(idstockactual, idproducto, idalmacen, stockactual)
VALUES('8', 5, 1, 9.000);
INSERT INTO bdcomer.stockactual
(idstockactual, idproducto, idalmacen, stockactual)
VALUES('9', 6, 1, 5.000);
INSERT INTO bdcomer.stockactual
(idstockactual, idproducto, idalmacen, stockactual)
VALUES('10', 7, 1, 80.000);


INSERT INTO bdcomer.almacen
(idalmacen, dscalmacen, direccion, idfilial)
VALUES(1, 'PUNTO DE VENTA', 'A', 1);
INSERT INTO bdcomer.almacen
(idalmacen, dscalmacen, direccion, idfilial)
VALUES(2, 'ALMACEN CENTRAL', 'A', 1);

INSERT INTO bdcomer.codigobarra
(idcodigobarra, codigo, idproducto)
VALUES('20180253f7f15a6a10c947c3879d6d6b6e6379db', '111111', 1);
INSERT INTO bdcomer.codigobarra
(idcodigobarra, codigo, idproducto)
VALUES('201802534c273b251e3d4e01b90b5dd562d8bbd6', '111113', 3);
INSERT INTO bdcomer.codigobarra
(idcodigobarra, codigo, idproducto)
VALUES('2018025367017bb39ae64412ad392ecdd18a59b4', '111114', 4);
INSERT INTO bdcomer.codigobarra
(idcodigobarra, codigo, idproducto)
VALUES('201802530926c859e5104d758252683b106a4f8a', '11115', 5);
INSERT INTO bdcomer.codigobarra
(idcodigobarra, codigo, idproducto)
VALUES('2018025383bedc3dd26f4389b2efeeca13174119', '111117', 7);
INSERT INTO bdcomer.codigobarra
(idcodigobarra, codigo, idproducto)
VALUES('20180253f4983c388bf74d26b8ca6ba4db0ba750', '111118', 6);
INSERT INTO bdcomer.codigobarra
(idcodigobarra, codigo, idproducto)
VALUES('2018025335d6318311f2442799624c419429b9a9', '122222', 2);
