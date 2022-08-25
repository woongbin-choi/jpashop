package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

//     ManyToOne과 XToOne 기본 fetch가 Eager 이다.
//     하나 가져오면 n + 1 형식으로 쿼리가 많이 날라감.
//     100개의 주문을 가져오면 똑같이 100개의 맴버를 찾게 된다.
//     그렇기 때문에 ManyToX 형식은 fetch를 Lazy로 하나하나 수정해주어야 한다.
//     XtoMany는 기본값이 Lazy여서 수정해줄 필요는 없다
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 모든 entity는 저장하려면 각자 persist 해야하지만 cascade를 활용하면 한번에 persist된다.
    // 저장 삭제 모두 적용

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orederDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Order, Cancel

    //==연관관계 메서드 (양쪽으로 다 저장하기 위함)
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }


}
