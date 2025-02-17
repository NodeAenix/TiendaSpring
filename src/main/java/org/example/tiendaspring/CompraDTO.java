package org.example.tiendaspring;

public class CompraDTO {

    private final String clienteNickname;
    private final Integer productoId;
    private final Integer cantidad;

    public CompraDTO(String clienteNickname, Integer productoId, Integer cantidad) {
        this.clienteNickname = clienteNickname;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public String getClienteNickname() {
        return clienteNickname;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

}
