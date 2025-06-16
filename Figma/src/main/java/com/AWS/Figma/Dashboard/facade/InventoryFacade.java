package com.AWS.Figma.Dashboard.facade;
import com.AWS.Figma.Dashboard.dto.DashboardSummaryDto;
import com.AWS.Figma.Dashboard.dto.ItemDto;
import com.AWS.Figma.Dashboard.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class InventoryFacade {

    @Autowired
    private ItemService itemService;

    public DashboardSummaryDto getDashboardData() {
        return itemService.getDashboardSummary();
    }

    public List<ItemDto> getNearingStockItemsPreview() {
        return itemService.getTop5NearingStockItems();
    }

    public List<ItemDto> getCompleteNearingStockItems() {
        return itemService.getAllNearingStockItems();
    }

    public List<ItemDto> getOutOfStockItemsPreview() {
        return itemService.getTop5OutOfStockItems();
    }

    public List<ItemDto> getCompleteOutOfStockItems() {
        return itemService.getAllOutOfStockItems();
    }

    public ItemDto addNewItem(ItemDto itemDto) {
        return itemService.createItem(itemDto);
    }

    public ItemDto modifyItem(Long id, ItemDto itemDto) {
        return itemService.updateItem(id, itemDto);
    }
}
