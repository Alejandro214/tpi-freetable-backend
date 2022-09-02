package com.restaurant.restaurantApi.service.impl;

import com.restaurant.restaurantApi.model.Combo;
import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IComboRepo;
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
    private IComboRepo iComboRepo;

    @Autowired
    private IProductRepo productRepo;


    @Override
    public Order saveOrder(Order order) {
        Order order1 = this.iOrderRepo.save(order);
        this.updateProductosPedidos(order1);
        return order1;
    }


    @Override
    public List<Order> getAllOrders(Integer idMesa) {
        Mesa mesa          = this.iMesaRepo.findById(idMesa).get();
        List<Order> orders = this.iOrderRepo.findAllByMesa(mesa);
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
    public void updateProductosPedidos(Order order) {
        for (Product p : order.getProducts()) {
            this.iOrderRepo.updateProductosPedidos(p.getIdProduct(), order.getIdOrder(), p.getCantProduct());
            p.addPedido(order);
        }
    }

    @Override
    public Order getOrderById(Integer idOrder) {
        return this.iOrderRepo.findById(idOrder).get();
    }

    @Override
    public Order updateOrder(Order order) {
        this.deleteOrder(order.getIdOrder());
        return this.saveOrder(order);
    }

    @Override
    public Order addProductOrder(Integer idProduct, Integer idOrder, Integer cant) {
        Order order = this.iOrderRepo.findById(idOrder).get();
        Product product = this.productRepo.findById(idProduct).get();
        if (this.productRepo.existeProductoInPedidosProductos(idOrder, idProduct).intValue() == 0) {
            this.iOrderRepo.updateProductosPedidos(idProduct, idOrder, cant);
            order.addPrinceTotal(product.getPrice() * cant.doubleValue());
            this.iOrderRepo.save(order);

        }else {
            this.productRepo.incrementarCantidadProductByIdProductAndIdOrder(idProduct,idOrder);
            order.addPrinceTotal(product.getPrice());
            this.iOrderRepo.save(order);
        }
        return this.getOrderById(idOrder);
    }


}
