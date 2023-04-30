package com.group.booking.Services.Order;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.group.booking.Common.Const;
import com.group.booking.Models.Addons.OrderRequest;
import com.group.booking.Models.Hotel.HotelModel;
import com.group.booking.Models.Hotel.RoomTypeModel;
import com.group.booking.Models.Order.HotelOrderDetailModel;
import com.group.booking.Models.Order.HotelOrderModel;
import com.group.booking.Models.Order.OrderGroupByStatus;
import com.group.booking.Repositories.Order.HotelOrderDetailRepository;
import com.group.booking.Repositories.Order.HotelOrderRepository;
import com.group.booking.Services.Hotel.HotelService;
import com.group.booking.Services.Hotel.RoomTypeService;
import com.group.booking.Utils.JwtUltil;

@Service
public class HotelOrderService {

    @Autowired
    private HotelOrderRepository hotelOrderRepository;
    @Autowired
    private HotelOrderDetailRepository hotelOrderDetailRepository;
    @Autowired
    private JwtUltil jwtUltil;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomTypeService roomTypeService;

    public HotelOrderModel getByOrderId(int orderId) {
        Optional<HotelOrderModel> foundOrder = hotelOrderRepository.findById(orderId);
        return foundOrder.isPresent() ? foundOrder.get() : null;
    }

    public List<HotelOrderModel> getAllByUserId(int hotelId) {
        try {
            List<HotelOrderModel> data = hotelOrderRepository.findAllByUserId(hotelId);
            return !data.isEmpty() ? data : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public HotelOrderModel postOrder(OrderRequest order) {
        try {
            HotelModel foundHotel = hotelService.foundByUserId(order.getUserId());
            String status = Const.AWAIT;
            if(foundHotel != null && foundHotel.getUserId() == order.getUserId()) {
                status = Const.COMFIRM;
            }
            HotelOrderModel orderReq = new HotelOrderModel(order, status);
            // save
            orderReq = hotelOrderRepository.save(orderReq);   
            HotelOrderModel orderRes = hotelOrderRepository.save(orderReq);
            order.getOrderDetails().forEach(data -> {
                RoomTypeModel foundRoom = roomTypeService.findById(data.getRoomId());
                if(foundRoom != null) {
                    hotelOrderDetailRepository.saveByOrder(orderRes.getId(), data.getRoomId(), data.getQuantity(), foundRoom.getPrice());
                }
            });
            // return
            orderReq.setOrderDetails(order.getOrderDetails().stream().map((obj) -> new HotelOrderDetailModel(obj, orderRes.getId(), 0)).collect(Collectors.toSet()));

            return orderReq;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return null;
    }

    public boolean cancelOrder(int orderId, String authorization) {
        try {
            String userId = jwtUltil.validateAndGetSubject(authorization);
            System.err.println("UserId: " + userId );
            if(!userId.equals("")) {
                HotelOrderModel foundOrder = getByOrderId(orderId);
                System.err.println("foundOrder: " + foundOrder.getId() );
                if(foundOrder != null && foundOrder.getUserId() == Integer.valueOf(userId)) {
                    System.out.println("Chuẩn bị save");
                    foundOrder.setStatusId(Const.CANCEL);
                    hotelOrderRepository.save(foundOrder);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Luu bị lỗi");
        }
        return false;
    }
    
    public List<OrderGroupByStatus> getByHotelIdAndGroupByHotelId(String authorization) {
        try {
            String userId = jwtUltil.validateAndGetSubject(authorization);
            if(!userId.equals("")) {
                HotelModel foundHotel = hotelService.foundByUserId(Integer.valueOf(userId));
                if(foundHotel != null) {
                    List<OrderGroupByStatus> foundOrders = hotelOrderRepository.findByHotelIdAndGroupByStatus(foundHotel.getId());
                    return !foundOrders.isEmpty() ? foundOrders : null;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Page<HotelOrderModel> getOrderByHotelIdAndStatus(String authorization, String status, int page, int size) {
        try {
            page -= 1;
            if(page < 0) page = 1;
            if(size < 5) page = 7;
            String userId = jwtUltil.validateAndGetSubject(authorization);
            if(!userId.equals("")) {
                HotelModel foundHotel = hotelService.foundByUserId(Integer.valueOf(userId));
                if(foundHotel != null) {
                    Pageable pageable = PageRequest.of(page, size);
                    switch (status) {
                        case Const.AWAIT:
                            return hotelOrderRepository.findAllByHotelIdAndStatusIdOrderByCheckinAsc(foundHotel.getId(), status, pageable);
                        case Const.COMFIRM:
                            return hotelOrderRepository.findAllByHotelIdAndStatusIdOrderByCheckinAsc(foundHotel.getId(), status, pageable);
                        case Const.ONGOING:
                            return hotelOrderRepository.findAllByHotelIdAndStatusIdOrderByCheckinAsc(foundHotel.getId(), status, pageable);
                        case Const.COMPLETE:
                            return hotelOrderRepository.findAllByHotelIdAndStatusIdOrderByCheckoutDesc(foundHotel.getId(), status, pageable);
                        case Const.CANCEL:
                            return hotelOrderRepository.findAllByHotelIdAndStatusIdOrderByCheckoutDesc(foundHotel.getId(), status, pageable);
                        default:
                            break;
                    }
                }
            }	
		} catch (Exception e) { }
        return null;
    }

    public boolean cancelOrderForManage(int orderId, String authorization) {
        try {
            // String userId = jwtUltil.validateAndGetSubject(authorization);
            // if(!userId.equals("")) {
            //     HotelModel foundHotel = hotelService.foundByUserId(Integer.valueOf(userId));
            //     HotelOrderModel foundOrder = hotelOrderService.getByOrderId(orderId);
            //     if(foundHotel != null && foundOrder != null && foundHotel.getId()== foundOrder.getHotelId()) {
            //         foundOrder.setStatusId(Const.CANCEL);
            //         hotelOrderRepository.save(foundOrder);
            //         return true;
            //     }
            // }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    public boolean changeStatus(int orderId, String status, String authorization) {
        try {
            String userId = jwtUltil.validateAndGetSubject(authorization);
            if(!userId.equals("")) {
                HotelModel foundHotel = hotelService.foundByUserId(Integer.valueOf(userId));
                Optional<HotelOrderModel> foundOrder = hotelOrderRepository.findById(orderId);
                if(foundHotel != null && foundOrder.isPresent() && foundHotel.getId() == foundOrder.get().getHotelId()) {
                    foundOrder.get().setStatusId(status);
                    hotelOrderRepository.save(foundOrder.get());
                    return true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    public List<RoomTypeModel> confirmOrder(String orders) {
        // convert from orders string -> list order string
        List<String> list = Arrays.asList(orders.split(","));
        // convert from list order string -> list array about (id, quantity)
        Stream<List<String>> l = list.stream().map(data -> Arrays.asList(data.split(":")));
        // find list for result
        List<RoomTypeModel> result = l.map(data -> roomTypeService
                                            .findById(Integer.valueOf(data.get(0)))
                                            .setQuantityReturnRoomType(Integer.valueOf(data.get(1))))
                                            .sorted(Comparator.comparing(RoomTypeModel::getPrice))
                                            .collect(Collectors.toList());
        return result;
    }
}
