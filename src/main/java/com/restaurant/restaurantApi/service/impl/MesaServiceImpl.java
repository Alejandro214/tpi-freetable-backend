package com.restaurant.restaurantApi.service.impl;


import com.restaurant.restaurantApi.model.Mesa;
import com.restaurant.restaurantApi.model.Order;
import com.restaurant.restaurantApi.model.Product;
import com.restaurant.restaurantApi.repo.IMesaRepo;
import com.restaurant.restaurantApi.service.inter.IMesaService;
import com.restaurant.restaurantApi.service.inter.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaServiceImpl implements IMesaService {

    @Autowired
    private IMesaRepo iMesaRepo;

    @Autowired
    private IOrderService orderService;

    @Override
    public Mesa saveMesa(Mesa mesa) {
        return this.iMesaRepo.save(mesa);
    }



    @Override
    public Mesa getMesaById(Integer idMesa) {
        return this.iMesaRepo.findById(idMesa).get();
    }

    @Override
    public List<Mesa> findAllMesas() {
        return (List<Mesa>) this.iMesaRepo.findAll();
    }

    @Override
    public Mesa addOrderByIdMesa(Integer idMesa, Order order) {
        Mesa mesa = this.getMesaById(idMesa);
        if(orderService.existsByMesaAndStatusOrder(mesa,"CONFIRMADO")){
            Order updateOrder = this.orderService.findOrderByMesaAndStatusOrder(mesa,"CONFIRMADO");
            updateOrder.setProducts(order.getProducts());
            this.orderService.saveOrder(updateOrder);
            return this.iMesaRepo.save(mesa);
        }
        order.setMesa(mesa);
        Order order1 = this.orderService.saveOrder(order);
        mesa.addOrder(order1);
        return this.iMesaRepo.save(mesa);
    }


  /*  private void updateProductosConfirmados(List<Product> productosConfirmados, Product product){
        if(existProductInProductsConfirmados(productosConfirmados,product)){
            productosConfirmados.forEach(product1 -> {
                if(product1.getIdProduct().equals(product.getIdProduct())){
                    product1.setCantProduct(product1.getCantProduct() + product.getCantProduct());
                }
            });
        }else {
            productosConfirmados.add(product);
        }
    }

    private boolean existProductInProductsConfirmados(List<Product> productosConfirmados,Product product){
        return  productosConfirmados.stream().anyMatch(product1 -> product1.getIdProduct().equals(product.getIdProduct()));
    }
   
   */

    @Override
    public Mesa changeEstadoMesa(Integer idMesa,String newEstadoMesa) {
        Mesa mesa = this.getMesaById(idMesa);
        mesa.setEstadoMesa(newEstadoMesa);
        return this.saveMesa(mesa);
    }

    @Override
    public void deleteMesaById(Integer idMesa) {
        this.iMesaRepo.deleteById(idMesa);
    }

    @Override
    public Mesa updatePositionMesa(Integer idMesa,Integer position) {
        Mesa mesa = this.getMesaById(idMesa);
        mesa.setPositionMesa(position);
        return this.saveMesa(mesa);
    }
}
