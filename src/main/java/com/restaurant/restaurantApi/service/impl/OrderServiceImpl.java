package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IMesaRepo;
import com.restaurant.restaurantApi.repo.IOrderRepo;
import com.restaurant.restaurantApi.repo.IProductRepo;
import com.restaurant.restaurantApi.service.inter.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                    this.iOrderRepo.updateProductosPedidos(product.getIdProduct(),order.getIdOrder(),
                            product.getCantProduct());
                }
        );
    }


    @Override
    public List<Order> getAllOrders(Integer idMesa,String statusOrder) {
        Mesa mesa          = this.iMesaRepo.findById(idMesa).get();
        List<Order> orders = this.iOrderRepo.findAllByMesaAndStatusOrder(mesa,statusOrder);
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
    public List<Product> getOrderConfirmado(Integer idMesa) {
        Mesa mesa          = this.iMesaRepo.findById(idMesa).get();
        List<Order> orders = this.iOrderRepo.findByMesaAndStatusOrder(mesa,"CONFIRMADO");
        List<Product> productsConfirmados = new ArrayList<>();
        orders.forEach(order -> {
            productsConfirmados.addAll(order.getProducts());
        });
        return productsConfirmados;
    }





}
