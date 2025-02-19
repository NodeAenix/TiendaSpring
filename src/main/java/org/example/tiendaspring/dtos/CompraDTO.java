package org.example.tiendaspring.dtos;

public class CompraDTO {

    private Integer id;
    private String clienteNickname;
    private Integer productoId;
    private Integer cantidad;

    public CompraDTO() {}

    public CompraDTO(Integer id, String clienteNickname, Integer productoId, Integer cantidad) {
        this.id = id;
        this.clienteNickname = clienteNickname;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
