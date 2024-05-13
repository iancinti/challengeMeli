# challengeMeli

##### Proseso de ejecucion local para pruebas:

1- Construimos imagen desde el directorio raiz con el siguiente comando:

    sudo docker compose build

2- Levantamos la imagen: 

    sudo docker compose up


3- El contenedor expone el servico a:

         localhost:8080/coupon

Se exponen los siguientes servicios:

**POST /coupon**

Toma los productos que recibe (productos favoritos) y devuelve una lista de productos que prodian cubrir el total o parcial del monto del cupon

***Body:***

```json
{
    "items": [
        "MLA901199374",
        "MLA931462756"
    ],
    "amount": 100000
}
```

***Respuesta:***

```json
{
    "items": [
        "MLA901199374"
    ],
    "total": 88023.67
}
```

**GET /coupon**

Devueve el top 5 de los items mas "canjeados"

Respuesta: 

```json
{
    "items": [
        "MLA901199374"
    ]
}
```
