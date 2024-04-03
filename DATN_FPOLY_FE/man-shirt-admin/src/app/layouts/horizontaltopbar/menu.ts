import { Role } from "src/app/core/models/auth.models";
import { MenuItem } from "./menu.model";

export const MENU: MenuItem[] = [
  {
    id: 1,
    label: "MENUITEMS.DASHBOARDS.TEXT",
    icon: "bx-home-circle",
    link: "/dashboard",
    role: Role.Manager,
  },
  {
    id: 50,
    label: "MENUITEMS.POS.TEXT",
    icon: "mdi mdi-laptop",
    link: "/pos",
    role: Role.Manager,
  },
  {
    id: 3,
    label: "MENUITEMS.ORDERS.TEXT",
    icon: "bx-cart-alt",
    role: Role.Manager,
    subItems: [
      {
        id: 5,
        label: "MENUITEMS.ORDERS.LIST.LISTORDERS",
        link: "/orders",
        parentId: 3,
        role: Role.Manager,
      },
      {
        id: 6,
        label: "MENUITEMS.ORDERS.LIST.EXCHANGE",
        link: "/exchange",
        parentId: 3,
        role: Role.Manager,
      },
    ],
  },
  {
    id: 8,
    label: "MENUITEMS.MANAGERS.TEXT",
    role: Role.Manager,
    subItems: [
      {
        id: 9,
        label: "MENUITEMS.PRODUCTS.TEXT",
        icon: "bx-cube",
        link: "/products",
        role: Role.Manager,
      },
      {
        id: 10,
        label: "MENUITEMS.CATEGORYS.TEXT",
        icon: "bx-cube",
        link: "/categorys",
        role: Role.Admin,
      },
      {
        id: 25,
        label: "MENUITEMS.CATEGORIES.TEXT",
        icon: "bx-cube",
        link: "/catergoryori",
        role: Role.Admin,
      },
      {
        id: 32,
        label: "MENUITEMS.DISCOUNTS.TEXT",
        icon: "bx-cube",
        link: "/discounts",
        role: Role.Admin,
      },
      {
        id: 35,
        label: "MENUITEMS.VOUCHERS.TEXT",
        icon: "bx-cube",
        link: "/vouchers",
        role: Role.Admin,
      },
      {
        id: 35,
        label: "MENUITEMS.RATING.TEXT",
        icon: "bx-cube",
        link: "/rating",
        role: Role.Manager,
      },
    ],
  },
  {
    id: 11,
    label: "MENUITEMS.ATTRIBUTES.TEXT",
    icon: "bx-server",
    role: Role.Manager,
    subItems: [
      {
        id: 12,
        label: "MENUITEMS.ATTRIBUTES.LIST.MATERIAL",
        link: "/attributes/material",
        parentId: 11,
        role: Role.Manager,
      },
      {
        id: 13,
        label: "MENUITEMS.ATTRIBUTES.LIST.COLOR",
        link: "/attributes/color",
        parentId: 11,
        role: Role.Manager,
      },
      {
        id: 14,
        label: "MENUITEMS.ATTRIBUTES.LIST.SIZE",
        link: "/attributes/size",
        parentId: 11,
        role: Role.Manager,
      },
      {
        id: 15,
        label: "MENUITEMS.ATTRIBUTES.LIST.DESIGN",
        link: "/attributes/design",
        parentId: 11,
        role: Role.Manager,
      },
      {
        id: 16,
        label: "MENUITEMS.ATTRIBUTES.LIST.FORM",
        link: "/attributes/form",
        parentId: 11,
        role: Role.Manager,
      },
      {
        id: 16,
        label: "MENUITEMS.ATTRIBUTES.LIST.SLEEVE",
        link: "/attributes/sleeve",
        parentId: 11,
        role: Role.Manager,
      },
      {
        id: 17,
        label: "MENUITEMS.ATTRIBUTES.LIST.COLLAR",
        link: "/attributes/collar",
        parentId: 11,
        role: Role.Manager,
      },
    ],
  },
  {
    id: 51,
    label: "MENUITEMS.CUSTOMER.TEXT",
    icon: "bxs-customize",
    link: "/customer",
    role: Role.Manager,
  },
  {
    id: 52,
    label: "MENUITEMS.EMPLOYEE.TEXT",
    icon: "bx-user",
    link: "/employee",
    role: Role.Admin,
  },
  {
    id: 53,
    label: "MENUITEMS.STATISTICAL.TEXT",
    icon: "bx-pie-chart-alt-2",
    role: Role.Admin,
    subItems: [
      {
        id: 1,
        label: "MENUITEMS.STATISTICAL.LIST.thongkesanpham",
        icon: "bx-cube",
        parentId: 53,
        link: "/STATISTICAL/sanpham",
        role: Role.Admin,
      },
      {
        id: 3,
        label: "MENUITEMS.STATISTICAL.LIST.thongkedoanhthu",
        icon: "bx-cube",
        parentId: 53,
        link: "/STATISTICAL/doanhthu",
        role: Role.Admin,
      },
    ],
  },
  {
    id: 54,
    label: "MENUITEMS.CONTACT.TEXT",
    icon: "bxs-contact",
    link: "/contact",
    role: Role.Admin,
  },
];
