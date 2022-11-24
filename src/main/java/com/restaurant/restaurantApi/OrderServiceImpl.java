package com.restaurant.restaurantApi;

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
    public List<Order> getAllOrdersPagados(Integer idMesa,String from, String to) {
        List<Order> orders = this.iOrderRepo.findAllByMesaAndStatusOrder(idMesa,from,to);
        orders.forEach(order -> {
                     order.setProducts(this.productRepo.findAllProductsByIdOrder(order.getIdOrder()));
                }
        );
        return orders;
    }


    @Override
    public void deleteOrder(Integer idOrder) {
        Order order = this.getOrderById(idOrder);
        if(order != null){
            order.deleteMesa();
            this.iOrderRepo.deleteOrderProductosPedidos(idOrder);
            this.iOrderRepo.delete(order);
        }
    }

    @Override
    public Order getOrderById(Integer idOrder) {
        return this.iOrderRepo.findById(idOrder).orElse(null);
    }

    @Override
    public Order getPedidoConfirmadoByIdMesa(Integer idMesa) {
        Mesa mesa          = this.iMesaRepo.findById(idMesa).get();
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
    @Override
    public Order updateOrder(Mesa mesa, Order order){
        Order updateOrder;
        if(this.existsByMesaAndStatusOrder(mesa,"CONFIRMADO")){
            updateOrder = this.findOrderByMesaAndStatusOrder(mesa,"CONFIRMADO");
            updateOrder.setProducts(order.getProducts());
        }else {
            order.setMesa(mesa);
            updateOrder = this.saveOrder(order);
            mesa.addOrder(updateOrder);
        }
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
    public void deleteOrders(List<Order> orders){
        orders.forEach(order -> {
            this.deleteOrder(order.getIdOrder());
        });
    }


    @Override
    public void deleteOrder(Order order) {
        this.iOrderRepo.delete(order);
    }


}
