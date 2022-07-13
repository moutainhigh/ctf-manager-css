package com.ctf.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 大节点状态枚举
 */
@Getter
@AllArgsConstructor
public enum HeaderStatusEnum {

    CLOSED("Closed","Closed"),
    PENDING("Pending","Pending"),
    CANCELLED("Cancelled","Cancelled"),
    PARTS_ON_HOLD("Parts On Hold","Parts On Hold"),
    ORDER_RELEASED("Order Released", "Order Released"),
    REPAIR_COMPLETED("Repair Completed", "Repair Completed"),
    IN_PROGRESS("In Progress", "In Progress"),
    Credit_Memo_Charge("Credit Memo Charge", "Credit Memo Charge"),
    Order_Changed("Order Changed", "Order Changed"),
    Order_Accepted("Order Accepted", "Order Accepted"),
    Post_to_DMR("Post to DMR", "Post to DMR"),
    Post_to_PO("Post to PO", "Post to PO"),
    POP_Required("POP Required", "POP Required"),
    Blocked("Blocked", "Blocked"),
    POP_Pending("POP Pending", "POP Pending"),
    Unit_Delivered_to_Customer("Unit Delivered to Customer", "Unit Delivered to Customer"),
    Accepted("Accepted", "Accepted"),
    Delivered("Delivered", "Delivered"),
    Unit_Received("Unit Received", "Unit Received"),
    Shipped("Shipped", "Shipped"),
    Fixed("Fixed", "Fixed"),
    Vendor_Cancel("Vendor Cancel", "Vendor Cancel"),
    Awaiting_Return("Awaiting Return", "Awaiting Return"),

    ;
    private String code;
    private String value;
}
