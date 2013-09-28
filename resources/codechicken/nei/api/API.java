package codechicken.nei.api;

import codechicken.nei.*;
import codechicken.nei.recipe.*;
import java.util.*;
import ur;

// Referenced classes of package codechicken.nei.api:
//            GuiInfo, ItemInfo, IStackPositioner, IOverlayHandler, 
//            INEIGuiHandler, LayoutStyle, IInfiniteItemHandler, IHighlightIdentifier

public class API
{

    public API()
    {
    }

    public static void registerRecipeHandler(ICraftingHandler handler)
    {
        GuiCraftingRecipe.registerRecipeHandler(handler);
    }

    public static void registerUsageHandler(IUsageHandler handler)
    {
        GuiUsageRecipe.registerUsageHandler(handler);
    }

    public static void registerGuiOverlay(Class class1, String string)
    {
        registerGuiOverlay(class1, string, 5, 11);
    }

    public static void registerGuiOverlay(Class class1, String string, int x, int y)
    {
        registerGuiOverlay(class1, string, ((IStackPositioner) (new OffsetPositioner(x, y))));
    }

    public static void registerGuiOverlay(Class classz, String ident, IStackPositioner positioner)
    {
        RecipeInfo.registerGuiOverlay(classz, ident, positioner);
    }

    public static void registerGuiOverlayHandler(Class classz, IOverlayHandler handler, String ident)
    {
        RecipeInfo.registerOverlayHandler(classz, handler, ident);
    }

    public static void registerNEIGuiHandler(INEIGuiHandler handler)
    {
        GuiInfo.guiHandlers.add(handler);
    }

    public static void hideItem(int itemID)
    {
        ItemInfo.excludeIds.add(Integer.valueOf(itemID));
    }

    public static void hideItems(Collection items)
    {
        ItemInfo.excludeIds.addAll(items);
    }

    public static void setOverrideName(int itemID, int itemDamage, String name)
    {
        ItemInfo.fallbackNames.put(new ItemHash(itemID, itemDamage), name);
    }

    public static void setItemDamageVariants(int itemID, ArrayList ranges)
    {
        ItemInfo.damageVariants.put(Integer.valueOf(itemID), ranges);
    }

    public static void setItemDamageVariants(int itemID, Collection damages)
    {
        setItemDamageVariants(itemID, NEIClientUtils.concatIntegersToRanges(new ArrayList(damages)));
    }

    public static void setMaxDamageException(int itemID, int maxDamage)
    {
        ArrayList damageset = new ArrayList();
        damageset.add(new int[] {
            0, maxDamage
        });
        setItemDamageVariants(itemID, damageset);
    }

    public static void addNBTItem(ur item)
    {
        ArrayList datalist = (ArrayList)ItemInfo.itemcompounds.get(Integer.valueOf(item.c));
        if(datalist == null)
        {
            datalist = new ArrayList();
            ItemInfo.itemcompounds.put(Integer.valueOf(item.c), datalist);
        }
        datalist.add(item);
    }

    public static void addSetRange(String setname, MultiItemRange range)
    {
        SubSetRangeTag tag = DropDownFile.dropDownInstance.getTag(setname);
        tag.saveTag = false;
        tag.setRange(range);
        DropDownFile.dropDownInstance.updateState();
    }

    public static SubSetRangeTag getRangeTag(String tagname)
    {
        return DropDownFile.dropDownInstance.getTag(tagname);
    }

    public static void addToRange(String setname, MultiItemRange range)
    {
        SubSetRangeTag tag = DropDownFile.dropDownInstance.getTag(setname);
        if(tag.validranges == null)
            tag.setRange(range);
        else
            tag.validranges.add(range);
    }

    public static void addSetting(codechicken.nei.GuiNEISettings.NEIOption o)
    {
        addSetting(codechicken/nei/GuiNEISettings, o);
    }

    public static void addSetting(Class guiclass, codechicken.nei.GuiNEISettings.NEIOption o)
    {
        LinkedList list = (LinkedList)GuiNEISettings.optionMap.get(guiclass);
        if(list == null)
        {
            list = new LinkedList();
            GuiNEISettings.optionMap.put(guiclass, list);
        }
        list.add(o);
    }

    public static void addKeyBind(String ident, String desc, int defaultKey)
    {
        NEIClientConfig.setDefaultKeyBinding(ident, defaultKey);
        addKeyBind(codechicken/nei/GuiNEIControls, new codechicken.nei.GuiNEIControls.NEIKeyBind(ident, desc));
    }

    public static void addKeyBind(Class guiclass, codechicken.nei.GuiNEIControls.NEIKeyBind key)
    {
        LinkedList list = (LinkedList)GuiNEIControls.keyBindMap.get(guiclass);
        if(list == null)
        {
            list = new LinkedList();
            GuiNEIControls.keyBindMap.put(guiclass, list);
        }
        list.add(key);
    }

    public static void addLayoutStyle(int styleID, LayoutStyle style)
    {
        LayoutManager.layoutStyles.put(Integer.valueOf(styleID), style);
    }

    public static void addInfiniteItemHandler(IInfiniteItemHandler handler)
    {
        ItemInfo.infiniteHandlers.addFirst(handler);
    }

    public static void registerHighlightIdentifier(int blockID, IHighlightIdentifier handler)
    {
        ArrayList handlers = (ArrayList)ItemInfo.highlightIdentifiers.get(Integer.valueOf(blockID));
        if(handlers == null)
        {
            handlers = new ArrayList();
            ItemInfo.highlightIdentifiers.put(Integer.valueOf(blockID), handlers);
        }
        handlers.add(handler);
    }

    public static void addFastTransferExemptSlot(Class slotClass)
    {
        ItemInfo.fastTransferExemptions.add(slotClass);
    }
}