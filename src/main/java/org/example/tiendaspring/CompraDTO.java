package org.example.tiendaspring;

public class CompraDTO {

    private String clienteNickname;
    private Integer productoId;
    private Integer cantidad;

    public CompraDTO(String clienteNickname, Integer productoId, Integer cantidad) {
        this.clienteNickname = clienteNickname;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public String getClienteNickname() {
        return clienteNickname;
    }

    public void setClienteNickname(String clienteNickname) {
        this.clienteNickname = clienteNickname;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

}
