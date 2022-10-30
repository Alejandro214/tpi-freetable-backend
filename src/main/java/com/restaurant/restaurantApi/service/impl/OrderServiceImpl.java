package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.repo.IMesaRepo;
import com.restaurant.restaurantApi.repo.IOrderRepo;
import com.restaurant.restaurantApi.repo.IProductRepo;
import com.restaurant.restaurantApi.service.inter.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderRepo iOrderRepo;

    @Autowired
    private IMesaRepo iMesaRepo;

    @Autowired
    private IProductRepo productRepo;


    @Override
    public Order saveOrder(Order order) {
        Order order1 = this.iOrderRepo.save(order);
        this.updateProductosPedidos(order1);
        return order1;
    }
    private void updateProductosPedidos(Order order){
        order.getProducts().forEach(product -> {
                    if(this.iOrderRepo.existProductoPedido(order.getIdOrder(),product.getIdProduct()).equals(1)){
                        this.iOrderRepo.updateProductosPedidos(product.getCantProduct(),product.getIdProduct(),order.getIdOrder());
                    }
                    else{
                        this.iOrderRepo.saveProductosPedidos(product.getIdProduct(),order.getIdOrder(),
                                product.getCantProduct());
                    }
                    product.setCantProduct(1);
                }
        );
    }
    @Override
    public List<Order> getAllOrdersPagados(Integer idMesa) {
        Mesa mesa          = this.iMesaRepo.findById(idMesa).get();
        List<Order> orders = this.iOrderRepo.findAllByMesaAndStatusOrder(mesa,"PAGADO");
        orders.forEach(order -> {
                     order.setProducts(this.productRepo.findAllProductsByIdOrder(order.getIdOrder()));
                }
        );
        return orders;
    }

    @Override
    public void deleteOrder(Integer idOrder) {
        Order order = this.getOrderById(idOrder);
        order.deleteMesa();
        this.iOrderRepo.delete(order);
        this.iOrderRepo.deleteOrderProductosPedidos(idOrder);
    }

    @Override
    public Order getOrderById(Integer idOrder) {
        return this.iOrderRepo.findById(idOrder).get();
    }

    @Override
    public Order getPedidoConfirmadoByIdMesa(Integer idMesa) {
        Mesa mesa          = this.iMesaRepo.findById(idMesa).orElse(null);
        Order pedidoConfirmado = this.findOrderByMesaAndStatusOrder(mesa,"CONFIRMADO");
        if(pedidoConfirmado != null) {
            pedidoConfirmado.getProducts().forEach(product -> {
                product.setCantProduct(this.getCantProductByIdMesaAndIdProduct(product.getIdProduct(), pedidoConfirmado.getIdOrder()));
            });
        }
        return  pedidoConfirmado;
    }

    private Integer getCantProductByIdMesaAndIdProduct(Integer idProduct, Integer idOrder){
        return this.productRepo.getCantProductByIdMesaAndIdProduct(idProduct,idOrder);
    }

    @Override
    public  Boolean existsByMesaAndStatusOrder(Mesa mesa,String statusOrder){
        return this.iOrderRepo.existsByMesaAndStatusOrder(mesa,statusOrder);
    }

    public Order updateOrder(Mesa mesa, Order order){
        Order updateOrder = this.findOrderByMesaAndStatusOrder(mesa,"CONFIRMADO");
        updateOrder.setProducts(order.getProducts());
        return this.saveOrder(updateOrder);
    }

    @Override
    public Order findOrderByMesaAndStatusOrder(Mesa mesa, String statusOrder) {
        return this.iOrderRepo.findByMesaAndStatusOrder(mesa,statusOrder);
    }

    @Override
    public Order pagarPedidoByMesaIdMesa(Integer idMesa) {
        Mesa mesa = this.iMesaRepo.findById(idMesa).get();
        Order order = this.iOrderRepo.findByMesaAndStatusOrder(mesa,"CONFIRMADO");
        order.setStatusOrder("PAGADO");
        return this.iOrderRepo.save(order);

    }


    @Override
    public void deleteOrder(Order order) {
        this.iOrderRepo.delete(order);
    }


}
