package OrderManagerPlus.testcases;

import OrderManagerPlus.pages.OrderManagerPlusPage;
import common.Config;
import common.MenuNavigation;
import order_entry.OrderEntryPlus_OrderPanel;
import order_entry.OrderEntryPlus_Service;

public class placeOrderTestcase extends OrderManagerPlusPage {

	OrderEntryPlus_OrderPanel obj = new OrderEntryPlus_OrderPanel();
	OrderEntryPlus_Service obj1 = new OrderEntryPlus_Service();

	public void placeOrderFromOrderEntryPlus(String drug_servicecode) throws InterruptedException {
		MenuNavigation.menuNav("Order Entry Plus");
		checkOrderPhysicianValue("orderingPhysicianList");
		checkCoSignListBox();
		obj.addOrderSetFromOrderPanel(Config.props.getProperty("order_set"));
		obj.completeOrderBasket();
		obj1.addService(true, Config.props.getProperty("edit_order_priority"), drug_servicecode);
		obj.completeOrderBasket();
		MenuNavigation.menuNav("Order Manager Plus");
	}

}
