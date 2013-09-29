package net.minecraft.src;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumStatus;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Session;
import net.minecraft.world.World;

public final class PlayerAPI
{
    private static final Class[] Class = new Class[] {PlayerAPI.class};
    private static final Class[] Classes = new Class[] {PlayerAPI.class, String.class};
    private static boolean isCreated;
    private static final Logger logger = Logger.getLogger("PlayerAPI");
    private static final List beforeAddExhaustionHookTypes = new LinkedList();
    private static final List overrideAddExhaustionHookTypes = new LinkedList();
    private static final List afterAddExhaustionHookTypes = new LinkedList();
    private final PlayerBase[] beforeAddExhaustionHooks;
    private final PlayerBase[] overrideAddExhaustionHooks;
    private final PlayerBase[] afterAddExhaustionHooks;
    public final boolean isAddExhaustionModded;
    private static final Map allBaseBeforeAddExhaustionSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeAddExhaustionInferiors = new Hashtable(0);
    private static final Map allBaseOverrideAddExhaustionSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideAddExhaustionInferiors = new Hashtable(0);
    private static final Map allBaseAfterAddExhaustionSuperiors = new Hashtable(0);
    private static final Map allBaseAfterAddExhaustionInferiors = new Hashtable(0);
    private static final List beforeAddMovementStatHookTypes = new LinkedList();
    private static final List overrideAddMovementStatHookTypes = new LinkedList();
    private static final List afterAddMovementStatHookTypes = new LinkedList();
    private final PlayerBase[] beforeAddMovementStatHooks;
    private final PlayerBase[] overrideAddMovementStatHooks;
    private final PlayerBase[] afterAddMovementStatHooks;
    public final boolean isAddMovementStatModded;
    private static final Map allBaseBeforeAddMovementStatSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeAddMovementStatInferiors = new Hashtable(0);
    private static final Map allBaseOverrideAddMovementStatSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideAddMovementStatInferiors = new Hashtable(0);
    private static final Map allBaseAfterAddMovementStatSuperiors = new Hashtable(0);
    private static final Map allBaseAfterAddMovementStatInferiors = new Hashtable(0);
    private static final List beforeAddStatHookTypes = new LinkedList();
    private static final List overrideAddStatHookTypes = new LinkedList();
    private static final List afterAddStatHookTypes = new LinkedList();
    private final PlayerBase[] beforeAddStatHooks;
    private final PlayerBase[] overrideAddStatHooks;
    private final PlayerBase[] afterAddStatHooks;
    public final boolean isAddStatModded;
    private static final Map allBaseBeforeAddStatSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeAddStatInferiors = new Hashtable(0);
    private static final Map allBaseOverrideAddStatSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideAddStatInferiors = new Hashtable(0);
    private static final Map allBaseAfterAddStatSuperiors = new Hashtable(0);
    private static final Map allBaseAfterAddStatInferiors = new Hashtable(0);
    private static final List beforeAttackEntityFromHookTypes = new LinkedList();
    private static final List overrideAttackEntityFromHookTypes = new LinkedList();
    private static final List afterAttackEntityFromHookTypes = new LinkedList();
    private final PlayerBase[] beforeAttackEntityFromHooks;
    private final PlayerBase[] overrideAttackEntityFromHooks;
    private final PlayerBase[] afterAttackEntityFromHooks;
    public final boolean isAttackEntityFromModded;
    private static final Map allBaseBeforeAttackEntityFromSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeAttackEntityFromInferiors = new Hashtable(0);
    private static final Map allBaseOverrideAttackEntityFromSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideAttackEntityFromInferiors = new Hashtable(0);
    private static final Map allBaseAfterAttackEntityFromSuperiors = new Hashtable(0);
    private static final Map allBaseAfterAttackEntityFromInferiors = new Hashtable(0);
    private static final List beforeAlertWolvesHookTypes = new LinkedList();
    private static final List overrideAlertWolvesHookTypes = new LinkedList();
    private static final List afterAlertWolvesHookTypes = new LinkedList();
    private final PlayerBase[] beforeAlertWolvesHooks;
    private final PlayerBase[] overrideAlertWolvesHooks;
    private final PlayerBase[] afterAlertWolvesHooks;
    public final boolean isAlertWolvesModded;
    private static final Map allBaseBeforeAlertWolvesSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeAlertWolvesInferiors = new Hashtable(0);
    private static final Map allBaseOverrideAlertWolvesSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideAlertWolvesInferiors = new Hashtable(0);
    private static final Map allBaseAfterAlertWolvesSuperiors = new Hashtable(0);
    private static final Map allBaseAfterAlertWolvesInferiors = new Hashtable(0);
    private static final List beforeAttackTargetEntityWithCurrentItemHookTypes = new LinkedList();
    private static final List overrideAttackTargetEntityWithCurrentItemHookTypes = new LinkedList();
    private static final List afterAttackTargetEntityWithCurrentItemHookTypes = new LinkedList();
    private final PlayerBase[] beforeAttackTargetEntityWithCurrentItemHooks;
    private final PlayerBase[] overrideAttackTargetEntityWithCurrentItemHooks;
    private final PlayerBase[] afterAttackTargetEntityWithCurrentItemHooks;
    public final boolean isAttackTargetEntityWithCurrentItemModded;
    private static final Map allBaseBeforeAttackTargetEntityWithCurrentItemSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeAttackTargetEntityWithCurrentItemInferiors = new Hashtable(0);
    private static final Map allBaseOverrideAttackTargetEntityWithCurrentItemSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideAttackTargetEntityWithCurrentItemInferiors = new Hashtable(0);
    private static final Map allBaseAfterAttackTargetEntityWithCurrentItemSuperiors = new Hashtable(0);
    private static final Map allBaseAfterAttackTargetEntityWithCurrentItemInferiors = new Hashtable(0);
    private static final List beforeCanBreatheUnderwaterHookTypes = new LinkedList();
    private static final List overrideCanBreatheUnderwaterHookTypes = new LinkedList();
    private static final List afterCanBreatheUnderwaterHookTypes = new LinkedList();
    private final PlayerBase[] beforeCanBreatheUnderwaterHooks;
    private final PlayerBase[] overrideCanBreatheUnderwaterHooks;
    private final PlayerBase[] afterCanBreatheUnderwaterHooks;
    public final boolean isCanBreatheUnderwaterModded;
    private static final Map allBaseBeforeCanBreatheUnderwaterSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeCanBreatheUnderwaterInferiors = new Hashtable(0);
    private static final Map allBaseOverrideCanBreatheUnderwaterSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideCanBreatheUnderwaterInferiors = new Hashtable(0);
    private static final Map allBaseAfterCanBreatheUnderwaterSuperiors = new Hashtable(0);
    private static final Map allBaseAfterCanBreatheUnderwaterInferiors = new Hashtable(0);
    private static final List beforeCanHarvestBlockHookTypes = new LinkedList();
    private static final List overrideCanHarvestBlockHookTypes = new LinkedList();
    private static final List afterCanHarvestBlockHookTypes = new LinkedList();
    private final PlayerBase[] beforeCanHarvestBlockHooks;
    private final PlayerBase[] overrideCanHarvestBlockHooks;
    private final PlayerBase[] afterCanHarvestBlockHooks;
    public final boolean isCanHarvestBlockModded;
    private static final Map allBaseBeforeCanHarvestBlockSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeCanHarvestBlockInferiors = new Hashtable(0);
    private static final Map allBaseOverrideCanHarvestBlockSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideCanHarvestBlockInferiors = new Hashtable(0);
    private static final Map allBaseAfterCanHarvestBlockSuperiors = new Hashtable(0);
    private static final Map allBaseAfterCanHarvestBlockInferiors = new Hashtable(0);
    private static final List beforeCanPlayerEditHookTypes = new LinkedList();
    private static final List overrideCanPlayerEditHookTypes = new LinkedList();
    private static final List afterCanPlayerEditHookTypes = new LinkedList();
    private final PlayerBase[] beforeCanPlayerEditHooks;
    private final PlayerBase[] overrideCanPlayerEditHooks;
    private final PlayerBase[] afterCanPlayerEditHooks;
    public final boolean isCanPlayerEditModded;
    private static final Map allBaseBeforeCanPlayerEditSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeCanPlayerEditInferiors = new Hashtable(0);
    private static final Map allBaseOverrideCanPlayerEditSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideCanPlayerEditInferiors = new Hashtable(0);
    private static final Map allBaseAfterCanPlayerEditSuperiors = new Hashtable(0);
    private static final Map allBaseAfterCanPlayerEditInferiors = new Hashtable(0);
    private static final List beforeCanTriggerWalkingHookTypes = new LinkedList();
    private static final List overrideCanTriggerWalkingHookTypes = new LinkedList();
    private static final List afterCanTriggerWalkingHookTypes = new LinkedList();
    private final PlayerBase[] beforeCanTriggerWalkingHooks;
    private final PlayerBase[] overrideCanTriggerWalkingHooks;
    private final PlayerBase[] afterCanTriggerWalkingHooks;
    public final boolean isCanTriggerWalkingModded;
    private static final Map allBaseBeforeCanTriggerWalkingSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeCanTriggerWalkingInferiors = new Hashtable(0);
    private static final Map allBaseOverrideCanTriggerWalkingSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideCanTriggerWalkingInferiors = new Hashtable(0);
    private static final Map allBaseAfterCanTriggerWalkingSuperiors = new Hashtable(0);
    private static final Map allBaseAfterCanTriggerWalkingInferiors = new Hashtable(0);
    private static final List beforeCloseScreenHookTypes = new LinkedList();
    private static final List overrideCloseScreenHookTypes = new LinkedList();
    private static final List afterCloseScreenHookTypes = new LinkedList();
    private final PlayerBase[] beforeCloseScreenHooks;
    private final PlayerBase[] overrideCloseScreenHooks;
    private final PlayerBase[] afterCloseScreenHooks;
    public final boolean isCloseScreenModded;
    private static final Map allBaseBeforeCloseScreenSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeCloseScreenInferiors = new Hashtable(0);
    private static final Map allBaseOverrideCloseScreenSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideCloseScreenInferiors = new Hashtable(0);
    private static final Map allBaseAfterCloseScreenSuperiors = new Hashtable(0);
    private static final Map allBaseAfterCloseScreenInferiors = new Hashtable(0);
    private static final List beforeDamageEntityHookTypes = new LinkedList();
    private static final List overrideDamageEntityHookTypes = new LinkedList();
    private static final List afterDamageEntityHookTypes = new LinkedList();
    private final PlayerBase[] beforeDamageEntityHooks;
    private final PlayerBase[] overrideDamageEntityHooks;
    private final PlayerBase[] afterDamageEntityHooks;
    public final boolean isDamageEntityModded;
    private static final Map allBaseBeforeDamageEntitySuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDamageEntityInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDamageEntitySuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDamageEntityInferiors = new Hashtable(0);
    private static final Map allBaseAfterDamageEntitySuperiors = new Hashtable(0);
    private static final Map allBaseAfterDamageEntityInferiors = new Hashtable(0);
    private static final List beforeDisplayGUIBrewingStandHookTypes = new LinkedList();
    private static final List overrideDisplayGUIBrewingStandHookTypes = new LinkedList();
    private static final List afterDisplayGUIBrewingStandHookTypes = new LinkedList();
    private final PlayerBase[] beforeDisplayGUIBrewingStandHooks;
    private final PlayerBase[] overrideDisplayGUIBrewingStandHooks;
    private final PlayerBase[] afterDisplayGUIBrewingStandHooks;
    public final boolean isDisplayGUIBrewingStandModded;
    private static final Map allBaseBeforeDisplayGUIBrewingStandSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDisplayGUIBrewingStandInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDisplayGUIBrewingStandSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDisplayGUIBrewingStandInferiors = new Hashtable(0);
    private static final Map allBaseAfterDisplayGUIBrewingStandSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDisplayGUIBrewingStandInferiors = new Hashtable(0);
    private static final List beforeDisplayGUIChestHookTypes = new LinkedList();
    private static final List overrideDisplayGUIChestHookTypes = new LinkedList();
    private static final List afterDisplayGUIChestHookTypes = new LinkedList();
    private final PlayerBase[] beforeDisplayGUIChestHooks;
    private final PlayerBase[] overrideDisplayGUIChestHooks;
    private final PlayerBase[] afterDisplayGUIChestHooks;
    public final boolean isDisplayGUIChestModded;
    private static final Map allBaseBeforeDisplayGUIChestSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDisplayGUIChestInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDisplayGUIChestSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDisplayGUIChestInferiors = new Hashtable(0);
    private static final Map allBaseAfterDisplayGUIChestSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDisplayGUIChestInferiors = new Hashtable(0);
    private static final List beforeDisplayGUIDispenserHookTypes = new LinkedList();
    private static final List overrideDisplayGUIDispenserHookTypes = new LinkedList();
    private static final List afterDisplayGUIDispenserHookTypes = new LinkedList();
    private final PlayerBase[] beforeDisplayGUIDispenserHooks;
    private final PlayerBase[] overrideDisplayGUIDispenserHooks;
    private final PlayerBase[] afterDisplayGUIDispenserHooks;
    public final boolean isDisplayGUIDispenserModded;
    private static final Map allBaseBeforeDisplayGUIDispenserSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDisplayGUIDispenserInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDisplayGUIDispenserSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDisplayGUIDispenserInferiors = new Hashtable(0);
    private static final Map allBaseAfterDisplayGUIDispenserSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDisplayGUIDispenserInferiors = new Hashtable(0);
    private static final List beforeDisplayGUIEditSignHookTypes = new LinkedList();
    private static final List overrideDisplayGUIEditSignHookTypes = new LinkedList();
    private static final List afterDisplayGUIEditSignHookTypes = new LinkedList();
    private final PlayerBase[] beforeDisplayGUIEditSignHooks;
    private final PlayerBase[] overrideDisplayGUIEditSignHooks;
    private final PlayerBase[] afterDisplayGUIEditSignHooks;
    public final boolean isDisplayGUIEditSignModded;
    private static final Map allBaseBeforeDisplayGUIEditSignSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDisplayGUIEditSignInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDisplayGUIEditSignSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDisplayGUIEditSignInferiors = new Hashtable(0);
    private static final Map allBaseAfterDisplayGUIEditSignSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDisplayGUIEditSignInferiors = new Hashtable(0);
    private static final List beforeDisplayGUIEnchantmentHookTypes = new LinkedList();
    private static final List overrideDisplayGUIEnchantmentHookTypes = new LinkedList();
    private static final List afterDisplayGUIEnchantmentHookTypes = new LinkedList();
    private final PlayerBase[] beforeDisplayGUIEnchantmentHooks;
    private final PlayerBase[] overrideDisplayGUIEnchantmentHooks;
    private final PlayerBase[] afterDisplayGUIEnchantmentHooks;
    public final boolean isDisplayGUIEnchantmentModded;
    private static final Map allBaseBeforeDisplayGUIEnchantmentSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDisplayGUIEnchantmentInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDisplayGUIEnchantmentSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDisplayGUIEnchantmentInferiors = new Hashtable(0);
    private static final Map allBaseAfterDisplayGUIEnchantmentSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDisplayGUIEnchantmentInferiors = new Hashtable(0);
    private static final List beforeDisplayGUIFurnaceHookTypes = new LinkedList();
    private static final List overrideDisplayGUIFurnaceHookTypes = new LinkedList();
    private static final List afterDisplayGUIFurnaceHookTypes = new LinkedList();
    private final PlayerBase[] beforeDisplayGUIFurnaceHooks;
    private final PlayerBase[] overrideDisplayGUIFurnaceHooks;
    private final PlayerBase[] afterDisplayGUIFurnaceHooks;
    public final boolean isDisplayGUIFurnaceModded;
    private static final Map allBaseBeforeDisplayGUIFurnaceSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDisplayGUIFurnaceInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDisplayGUIFurnaceSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDisplayGUIFurnaceInferiors = new Hashtable(0);
    private static final Map allBaseAfterDisplayGUIFurnaceSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDisplayGUIFurnaceInferiors = new Hashtable(0);
    private static final List beforeDisplayGUIWorkbenchHookTypes = new LinkedList();
    private static final List overrideDisplayGUIWorkbenchHookTypes = new LinkedList();
    private static final List afterDisplayGUIWorkbenchHookTypes = new LinkedList();
    private final PlayerBase[] beforeDisplayGUIWorkbenchHooks;
    private final PlayerBase[] overrideDisplayGUIWorkbenchHooks;
    private final PlayerBase[] afterDisplayGUIWorkbenchHooks;
    public final boolean isDisplayGUIWorkbenchModded;
    private static final Map allBaseBeforeDisplayGUIWorkbenchSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDisplayGUIWorkbenchInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDisplayGUIWorkbenchSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDisplayGUIWorkbenchInferiors = new Hashtable(0);
    private static final Map allBaseAfterDisplayGUIWorkbenchSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDisplayGUIWorkbenchInferiors = new Hashtable(0);
    private static final List beforeDropOneItemHookTypes = new LinkedList();
    private static final List overrideDropOneItemHookTypes = new LinkedList();
    private static final List afterDropOneItemHookTypes = new LinkedList();
    private final PlayerBase[] beforeDropOneItemHooks;
    private final PlayerBase[] overrideDropOneItemHooks;
    private final PlayerBase[] afterDropOneItemHooks;
    public final boolean isDropOneItemModded;
    private static final Map allBaseBeforeDropOneItemSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDropOneItemInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDropOneItemSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDropOneItemInferiors = new Hashtable(0);
    private static final Map allBaseAfterDropOneItemSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDropOneItemInferiors = new Hashtable(0);
    private static final List beforeDropPlayerItemHookTypes = new LinkedList();
    private static final List overrideDropPlayerItemHookTypes = new LinkedList();
    private static final List afterDropPlayerItemHookTypes = new LinkedList();
    private final PlayerBase[] beforeDropPlayerItemHooks;
    private final PlayerBase[] overrideDropPlayerItemHooks;
    private final PlayerBase[] afterDropPlayerItemHooks;
    public final boolean isDropPlayerItemModded;
    private static final Map allBaseBeforeDropPlayerItemSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDropPlayerItemInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDropPlayerItemSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDropPlayerItemInferiors = new Hashtable(0);
    private static final Map allBaseAfterDropPlayerItemSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDropPlayerItemInferiors = new Hashtable(0);
    private static final List beforeDropPlayerItemWithRandomChoiceHookTypes = new LinkedList();
    private static final List overrideDropPlayerItemWithRandomChoiceHookTypes = new LinkedList();
    private static final List afterDropPlayerItemWithRandomChoiceHookTypes = new LinkedList();
    private final PlayerBase[] beforeDropPlayerItemWithRandomChoiceHooks;
    private final PlayerBase[] overrideDropPlayerItemWithRandomChoiceHooks;
    private final PlayerBase[] afterDropPlayerItemWithRandomChoiceHooks;
    public final boolean isDropPlayerItemWithRandomChoiceModded;
    private static final Map allBaseBeforeDropPlayerItemWithRandomChoiceSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDropPlayerItemWithRandomChoiceInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDropPlayerItemWithRandomChoiceSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDropPlayerItemWithRandomChoiceInferiors = new Hashtable(0);
    private static final Map allBaseAfterDropPlayerItemWithRandomChoiceSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDropPlayerItemWithRandomChoiceInferiors = new Hashtable(0);
    private static final List beforeFallHookTypes = new LinkedList();
    private static final List overrideFallHookTypes = new LinkedList();
    private static final List afterFallHookTypes = new LinkedList();
    private final PlayerBase[] beforeFallHooks;
    private final PlayerBase[] overrideFallHooks;
    private final PlayerBase[] afterFallHooks;
    public final boolean isFallModded;
    private static final Map allBaseBeforeFallSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeFallInferiors = new Hashtable(0);
    private static final Map allBaseOverrideFallSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideFallInferiors = new Hashtable(0);
    private static final Map allBaseAfterFallSuperiors = new Hashtable(0);
    private static final Map allBaseAfterFallInferiors = new Hashtable(0);
    private static final List beforeGetBrightnessHookTypes = new LinkedList();
    private static final List overrideGetBrightnessHookTypes = new LinkedList();
    private static final List afterGetBrightnessHookTypes = new LinkedList();
    private final PlayerBase[] beforeGetBrightnessHooks;
    private final PlayerBase[] overrideGetBrightnessHooks;
    private final PlayerBase[] afterGetBrightnessHooks;
    public final boolean isGetBrightnessModded;
    private static final Map allBaseBeforeGetBrightnessSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetBrightnessInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetBrightnessSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetBrightnessInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetBrightnessSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetBrightnessInferiors = new Hashtable(0);
    private static final List beforeGetBrightnessForRenderHookTypes = new LinkedList();
    private static final List overrideGetBrightnessForRenderHookTypes = new LinkedList();
    private static final List afterGetBrightnessForRenderHookTypes = new LinkedList();
    private final PlayerBase[] beforeGetBrightnessForRenderHooks;
    private final PlayerBase[] overrideGetBrightnessForRenderHooks;
    private final PlayerBase[] afterGetBrightnessForRenderHooks;
    public final boolean isGetBrightnessForRenderModded;
    private static final Map allBaseBeforeGetBrightnessForRenderSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetBrightnessForRenderInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetBrightnessForRenderSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetBrightnessForRenderInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetBrightnessForRenderSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetBrightnessForRenderInferiors = new Hashtable(0);
    private static final List beforeGetCurrentPlayerStrVsBlockHookTypes = new LinkedList();
    private static final List overrideGetCurrentPlayerStrVsBlockHookTypes = new LinkedList();
    private static final List afterGetCurrentPlayerStrVsBlockHookTypes = new LinkedList();
    private final PlayerBase[] beforeGetCurrentPlayerStrVsBlockHooks;
    private final PlayerBase[] overrideGetCurrentPlayerStrVsBlockHooks;
    private final PlayerBase[] afterGetCurrentPlayerStrVsBlockHooks;
    public final boolean isGetCurrentPlayerStrVsBlockModded;
    private static final Map allBaseBeforeGetCurrentPlayerStrVsBlockSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetCurrentPlayerStrVsBlockInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetCurrentPlayerStrVsBlockSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetCurrentPlayerStrVsBlockInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetCurrentPlayerStrVsBlockSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetCurrentPlayerStrVsBlockInferiors = new Hashtable(0);
    private static final List beforeGetDistanceSqHookTypes = new LinkedList();
    private static final List overrideGetDistanceSqHookTypes = new LinkedList();
    private static final List afterGetDistanceSqHookTypes = new LinkedList();
    private final PlayerBase[] beforeGetDistanceSqHooks;
    private final PlayerBase[] overrideGetDistanceSqHooks;
    private final PlayerBase[] afterGetDistanceSqHooks;
    public final boolean isGetDistanceSqModded;
    private static final Map allBaseBeforeGetDistanceSqSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetDistanceSqInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetDistanceSqSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetDistanceSqInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetDistanceSqSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetDistanceSqInferiors = new Hashtable(0);
    private static final List beforeGetDistanceSqToEntityHookTypes = new LinkedList();
    private static final List overrideGetDistanceSqToEntityHookTypes = new LinkedList();
    private static final List afterGetDistanceSqToEntityHookTypes = new LinkedList();
    private final PlayerBase[] beforeGetDistanceSqToEntityHooks;
    private final PlayerBase[] overrideGetDistanceSqToEntityHooks;
    private final PlayerBase[] afterGetDistanceSqToEntityHooks;
    public final boolean isGetDistanceSqToEntityModded;
    private static final Map allBaseBeforeGetDistanceSqToEntitySuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetDistanceSqToEntityInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetDistanceSqToEntitySuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetDistanceSqToEntityInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetDistanceSqToEntitySuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetDistanceSqToEntityInferiors = new Hashtable(0);
    private static final List beforeGetFOVMultiplierHookTypes = new LinkedList();
    private static final List overrideGetFOVMultiplierHookTypes = new LinkedList();
    private static final List afterGetFOVMultiplierHookTypes = new LinkedList();
    private final PlayerBase[] beforeGetFOVMultiplierHooks;
    private final PlayerBase[] overrideGetFOVMultiplierHooks;
    private final PlayerBase[] afterGetFOVMultiplierHooks;
    public final boolean isGetFOVMultiplierModded;
    private static final Map allBaseBeforeGetFOVMultiplierSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetFOVMultiplierInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetFOVMultiplierSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetFOVMultiplierInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetFOVMultiplierSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetFOVMultiplierInferiors = new Hashtable(0);
    private static final List beforeGetHurtSoundHookTypes = new LinkedList();
    private static final List overrideGetHurtSoundHookTypes = new LinkedList();
    private static final List afterGetHurtSoundHookTypes = new LinkedList();
    private final PlayerBase[] beforeGetHurtSoundHooks;
    private final PlayerBase[] overrideGetHurtSoundHooks;
    private final PlayerBase[] afterGetHurtSoundHooks;
    public final boolean isGetHurtSoundModded;
    private static final Map allBaseBeforeGetHurtSoundSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetHurtSoundInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetHurtSoundSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetHurtSoundInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetHurtSoundSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetHurtSoundInferiors = new Hashtable(0);
    private static final List beforeGetItemIconHookTypes = new LinkedList();
    private static final List overrideGetItemIconHookTypes = new LinkedList();
    private static final List afterGetItemIconHookTypes = new LinkedList();
    private final PlayerBase[] beforeGetItemIconHooks;
    private final PlayerBase[] overrideGetItemIconHooks;
    private final PlayerBase[] afterGetItemIconHooks;
    public final boolean isGetItemIconModded;
    private static final Map allBaseBeforeGetItemIconSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetItemIconInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetItemIconSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetItemIconInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetItemIconSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetItemIconInferiors = new Hashtable(0);
    private static final List beforeGetMaxHealthHookTypes = new LinkedList();
    private static final List overrideGetMaxHealthHookTypes = new LinkedList();
    private static final List afterGetMaxHealthHookTypes = new LinkedList();
    private final PlayerBase[] beforeGetMaxHealthHooks;
    private final PlayerBase[] overrideGetMaxHealthHooks;
    private final PlayerBase[] afterGetMaxHealthHooks;
    public final boolean isGetMaxHealthModded;
    private static final Map allBaseBeforeGetMaxHealthSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetMaxHealthInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetMaxHealthSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetMaxHealthInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetMaxHealthSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetMaxHealthInferiors = new Hashtable(0);
    private static final List beforeGetSleepTimerHookTypes = new LinkedList();
    private static final List overrideGetSleepTimerHookTypes = new LinkedList();
    private static final List afterGetSleepTimerHookTypes = new LinkedList();
    private final PlayerBase[] beforeGetSleepTimerHooks;
    private final PlayerBase[] overrideGetSleepTimerHooks;
    private final PlayerBase[] afterGetSleepTimerHooks;
    public final boolean isGetSleepTimerModded;
    private static final Map allBaseBeforeGetSleepTimerSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetSleepTimerInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetSleepTimerSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetSleepTimerInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetSleepTimerSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetSleepTimerInferiors = new Hashtable(0);
    private static final List beforeGetSpeedModifierHookTypes = new LinkedList();
    private static final List overrideGetSpeedModifierHookTypes = new LinkedList();
    private static final List afterGetSpeedModifierHookTypes = new LinkedList();
    private final PlayerBase[] beforeGetSpeedModifierHooks;
    private final PlayerBase[] overrideGetSpeedModifierHooks;
    private final PlayerBase[] afterGetSpeedModifierHooks;
    public final boolean isGetSpeedModifierModded;
    private static final Map allBaseBeforeGetSpeedModifierSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetSpeedModifierInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetSpeedModifierSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetSpeedModifierInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetSpeedModifierSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetSpeedModifierInferiors = new Hashtable(0);
    private static final List beforeHandleLavaMovementHookTypes = new LinkedList();
    private static final List overrideHandleLavaMovementHookTypes = new LinkedList();
    private static final List afterHandleLavaMovementHookTypes = new LinkedList();
    private final PlayerBase[] beforeHandleLavaMovementHooks;
    private final PlayerBase[] overrideHandleLavaMovementHooks;
    private final PlayerBase[] afterHandleLavaMovementHooks;
    public final boolean isHandleLavaMovementModded;
    private static final Map allBaseBeforeHandleLavaMovementSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeHandleLavaMovementInferiors = new Hashtable(0);
    private static final Map allBaseOverrideHandleLavaMovementSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideHandleLavaMovementInferiors = new Hashtable(0);
    private static final Map allBaseAfterHandleLavaMovementSuperiors = new Hashtable(0);
    private static final Map allBaseAfterHandleLavaMovementInferiors = new Hashtable(0);
    private static final List beforeHandleWaterMovementHookTypes = new LinkedList();
    private static final List overrideHandleWaterMovementHookTypes = new LinkedList();
    private static final List afterHandleWaterMovementHookTypes = new LinkedList();
    private final PlayerBase[] beforeHandleWaterMovementHooks;
    private final PlayerBase[] overrideHandleWaterMovementHooks;
    private final PlayerBase[] afterHandleWaterMovementHooks;
    public final boolean isHandleWaterMovementModded;
    private static final Map allBaseBeforeHandleWaterMovementSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeHandleWaterMovementInferiors = new Hashtable(0);
    private static final Map allBaseOverrideHandleWaterMovementSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideHandleWaterMovementInferiors = new Hashtable(0);
    private static final Map allBaseAfterHandleWaterMovementSuperiors = new Hashtable(0);
    private static final Map allBaseAfterHandleWaterMovementInferiors = new Hashtable(0);
    private static final List beforeHealHookTypes = new LinkedList();
    private static final List overrideHealHookTypes = new LinkedList();
    private static final List afterHealHookTypes = new LinkedList();
    private final PlayerBase[] beforeHealHooks;
    private final PlayerBase[] overrideHealHooks;
    private final PlayerBase[] afterHealHooks;
    public final boolean isHealModded;
    private static final Map allBaseBeforeHealSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeHealInferiors = new Hashtable(0);
    private static final Map allBaseOverrideHealSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideHealInferiors = new Hashtable(0);
    private static final Map allBaseAfterHealSuperiors = new Hashtable(0);
    private static final Map allBaseAfterHealInferiors = new Hashtable(0);
    private static final List beforeInteractHookTypes = new LinkedList();
    private static final List overrideInteractHookTypes = new LinkedList();
    private static final List afterInteractHookTypes = new LinkedList();
    private final PlayerBase[] beforeInteractHooks;
    private final PlayerBase[] overrideInteractHooks;
    private final PlayerBase[] afterInteractHooks;
    public final boolean isInteractModded;
    private static final Map allBaseBeforeInteractSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeInteractInferiors = new Hashtable(0);
    private static final Map allBaseOverrideInteractSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideInteractInferiors = new Hashtable(0);
    private static final Map allBaseAfterInteractSuperiors = new Hashtable(0);
    private static final Map allBaseAfterInteractInferiors = new Hashtable(0);
    private static final List beforeIsEntityInsideOpaqueBlockHookTypes = new LinkedList();
    private static final List overrideIsEntityInsideOpaqueBlockHookTypes = new LinkedList();
    private static final List afterIsEntityInsideOpaqueBlockHookTypes = new LinkedList();
    private final PlayerBase[] beforeIsEntityInsideOpaqueBlockHooks;
    private final PlayerBase[] overrideIsEntityInsideOpaqueBlockHooks;
    private final PlayerBase[] afterIsEntityInsideOpaqueBlockHooks;
    public final boolean isIsEntityInsideOpaqueBlockModded;
    private static final Map allBaseBeforeIsEntityInsideOpaqueBlockSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeIsEntityInsideOpaqueBlockInferiors = new Hashtable(0);
    private static final Map allBaseOverrideIsEntityInsideOpaqueBlockSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideIsEntityInsideOpaqueBlockInferiors = new Hashtable(0);
    private static final Map allBaseAfterIsEntityInsideOpaqueBlockSuperiors = new Hashtable(0);
    private static final Map allBaseAfterIsEntityInsideOpaqueBlockInferiors = new Hashtable(0);
    private static final List beforeIsInWaterHookTypes = new LinkedList();
    private static final List overrideIsInWaterHookTypes = new LinkedList();
    private static final List afterIsInWaterHookTypes = new LinkedList();
    private final PlayerBase[] beforeIsInWaterHooks;
    private final PlayerBase[] overrideIsInWaterHooks;
    private final PlayerBase[] afterIsInWaterHooks;
    public final boolean isIsInWaterModded;
    private static final Map allBaseBeforeIsInWaterSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeIsInWaterInferiors = new Hashtable(0);
    private static final Map allBaseOverrideIsInWaterSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideIsInWaterInferiors = new Hashtable(0);
    private static final Map allBaseAfterIsInWaterSuperiors = new Hashtable(0);
    private static final Map allBaseAfterIsInWaterInferiors = new Hashtable(0);
    private static final List beforeIsInsideOfMaterialHookTypes = new LinkedList();
    private static final List overrideIsInsideOfMaterialHookTypes = new LinkedList();
    private static final List afterIsInsideOfMaterialHookTypes = new LinkedList();
    private final PlayerBase[] beforeIsInsideOfMaterialHooks;
    private final PlayerBase[] overrideIsInsideOfMaterialHooks;
    private final PlayerBase[] afterIsInsideOfMaterialHooks;
    public final boolean isIsInsideOfMaterialModded;
    private static final Map allBaseBeforeIsInsideOfMaterialSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeIsInsideOfMaterialInferiors = new Hashtable(0);
    private static final Map allBaseOverrideIsInsideOfMaterialSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideIsInsideOfMaterialInferiors = new Hashtable(0);
    private static final Map allBaseAfterIsInsideOfMaterialSuperiors = new Hashtable(0);
    private static final Map allBaseAfterIsInsideOfMaterialInferiors = new Hashtable(0);
    private static final List beforeIsOnLadderHookTypes = new LinkedList();
    private static final List overrideIsOnLadderHookTypes = new LinkedList();
    private static final List afterIsOnLadderHookTypes = new LinkedList();
    private final PlayerBase[] beforeIsOnLadderHooks;
    private final PlayerBase[] overrideIsOnLadderHooks;
    private final PlayerBase[] afterIsOnLadderHooks;
    public final boolean isIsOnLadderModded;
    private static final Map allBaseBeforeIsOnLadderSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeIsOnLadderInferiors = new Hashtable(0);
    private static final Map allBaseOverrideIsOnLadderSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideIsOnLadderInferiors = new Hashtable(0);
    private static final Map allBaseAfterIsOnLadderSuperiors = new Hashtable(0);
    private static final Map allBaseAfterIsOnLadderInferiors = new Hashtable(0);
    private static final List beforeIsPlayerSleepingHookTypes = new LinkedList();
    private static final List overrideIsPlayerSleepingHookTypes = new LinkedList();
    private static final List afterIsPlayerSleepingHookTypes = new LinkedList();
    private final PlayerBase[] beforeIsPlayerSleepingHooks;
    private final PlayerBase[] overrideIsPlayerSleepingHooks;
    private final PlayerBase[] afterIsPlayerSleepingHooks;
    public final boolean isIsPlayerSleepingModded;
    private static final Map allBaseBeforeIsPlayerSleepingSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeIsPlayerSleepingInferiors = new Hashtable(0);
    private static final Map allBaseOverrideIsPlayerSleepingSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideIsPlayerSleepingInferiors = new Hashtable(0);
    private static final Map allBaseAfterIsPlayerSleepingSuperiors = new Hashtable(0);
    private static final Map allBaseAfterIsPlayerSleepingInferiors = new Hashtable(0);
    private static final List beforeIsSneakingHookTypes = new LinkedList();
    private static final List overrideIsSneakingHookTypes = new LinkedList();
    private static final List afterIsSneakingHookTypes = new LinkedList();
    private final PlayerBase[] beforeIsSneakingHooks;
    private final PlayerBase[] overrideIsSneakingHooks;
    private final PlayerBase[] afterIsSneakingHooks;
    public final boolean isIsSneakingModded;
    private static final Map allBaseBeforeIsSneakingSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeIsSneakingInferiors = new Hashtable(0);
    private static final Map allBaseOverrideIsSneakingSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideIsSneakingInferiors = new Hashtable(0);
    private static final Map allBaseAfterIsSneakingSuperiors = new Hashtable(0);
    private static final Map allBaseAfterIsSneakingInferiors = new Hashtable(0);
    private static final List beforeIsSprintingHookTypes = new LinkedList();
    private static final List overrideIsSprintingHookTypes = new LinkedList();
    private static final List afterIsSprintingHookTypes = new LinkedList();
    private final PlayerBase[] beforeIsSprintingHooks;
    private final PlayerBase[] overrideIsSprintingHooks;
    private final PlayerBase[] afterIsSprintingHooks;
    public final boolean isIsSprintingModded;
    private static final Map allBaseBeforeIsSprintingSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeIsSprintingInferiors = new Hashtable(0);
    private static final Map allBaseOverrideIsSprintingSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideIsSprintingInferiors = new Hashtable(0);
    private static final Map allBaseAfterIsSprintingSuperiors = new Hashtable(0);
    private static final Map allBaseAfterIsSprintingInferiors = new Hashtable(0);
    private static final List beforeJumpHookTypes = new LinkedList();
    private static final List overrideJumpHookTypes = new LinkedList();
    private static final List afterJumpHookTypes = new LinkedList();
    private final PlayerBase[] beforeJumpHooks;
    private final PlayerBase[] overrideJumpHooks;
    private final PlayerBase[] afterJumpHooks;
    public final boolean isJumpModded;
    private static final Map allBaseBeforeJumpSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeJumpInferiors = new Hashtable(0);
    private static final Map allBaseOverrideJumpSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideJumpInferiors = new Hashtable(0);
    private static final Map allBaseAfterJumpSuperiors = new Hashtable(0);
    private static final Map allBaseAfterJumpInferiors = new Hashtable(0);
    private static final List beforeKnockBackHookTypes = new LinkedList();
    private static final List overrideKnockBackHookTypes = new LinkedList();
    private static final List afterKnockBackHookTypes = new LinkedList();
    private final PlayerBase[] beforeKnockBackHooks;
    private final PlayerBase[] overrideKnockBackHooks;
    private final PlayerBase[] afterKnockBackHooks;
    public final boolean isKnockBackModded;
    private static final Map allBaseBeforeKnockBackSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeKnockBackInferiors = new Hashtable(0);
    private static final Map allBaseOverrideKnockBackSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideKnockBackInferiors = new Hashtable(0);
    private static final Map allBaseAfterKnockBackSuperiors = new Hashtable(0);
    private static final Map allBaseAfterKnockBackInferiors = new Hashtable(0);
    private static final List beforeMoveEntityHookTypes = new LinkedList();
    private static final List overrideMoveEntityHookTypes = new LinkedList();
    private static final List afterMoveEntityHookTypes = new LinkedList();
    private final PlayerBase[] beforeMoveEntityHooks;
    private final PlayerBase[] overrideMoveEntityHooks;
    private final PlayerBase[] afterMoveEntityHooks;
    public final boolean isMoveEntityModded;
    private static final Map allBaseBeforeMoveEntitySuperiors = new Hashtable(0);
    private static final Map allBaseBeforeMoveEntityInferiors = new Hashtable(0);
    private static final Map allBaseOverrideMoveEntitySuperiors = new Hashtable(0);
    private static final Map allBaseOverrideMoveEntityInferiors = new Hashtable(0);
    private static final Map allBaseAfterMoveEntitySuperiors = new Hashtable(0);
    private static final Map allBaseAfterMoveEntityInferiors = new Hashtable(0);
    private static final List beforeMoveEntityWithHeadingHookTypes = new LinkedList();
    private static final List overrideMoveEntityWithHeadingHookTypes = new LinkedList();
    private static final List afterMoveEntityWithHeadingHookTypes = new LinkedList();
    private final PlayerBase[] beforeMoveEntityWithHeadingHooks;
    private final PlayerBase[] overrideMoveEntityWithHeadingHooks;
    private final PlayerBase[] afterMoveEntityWithHeadingHooks;
    public final boolean isMoveEntityWithHeadingModded;
    private static final Map allBaseBeforeMoveEntityWithHeadingSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeMoveEntityWithHeadingInferiors = new Hashtable(0);
    private static final Map allBaseOverrideMoveEntityWithHeadingSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideMoveEntityWithHeadingInferiors = new Hashtable(0);
    private static final Map allBaseAfterMoveEntityWithHeadingSuperiors = new Hashtable(0);
    private static final Map allBaseAfterMoveEntityWithHeadingInferiors = new Hashtable(0);
    private static final List beforeMoveFlyingHookTypes = new LinkedList();
    private static final List overrideMoveFlyingHookTypes = new LinkedList();
    private static final List afterMoveFlyingHookTypes = new LinkedList();
    private final PlayerBase[] beforeMoveFlyingHooks;
    private final PlayerBase[] overrideMoveFlyingHooks;
    private final PlayerBase[] afterMoveFlyingHooks;
    public final boolean isMoveFlyingModded;
    private static final Map allBaseBeforeMoveFlyingSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeMoveFlyingInferiors = new Hashtable(0);
    private static final Map allBaseOverrideMoveFlyingSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideMoveFlyingInferiors = new Hashtable(0);
    private static final Map allBaseAfterMoveFlyingSuperiors = new Hashtable(0);
    private static final Map allBaseAfterMoveFlyingInferiors = new Hashtable(0);
    private static final List beforeOnDeathHookTypes = new LinkedList();
    private static final List overrideOnDeathHookTypes = new LinkedList();
    private static final List afterOnDeathHookTypes = new LinkedList();
    private final PlayerBase[] beforeOnDeathHooks;
    private final PlayerBase[] overrideOnDeathHooks;
    private final PlayerBase[] afterOnDeathHooks;
    public final boolean isOnDeathModded;
    private static final Map allBaseBeforeOnDeathSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeOnDeathInferiors = new Hashtable(0);
    private static final Map allBaseOverrideOnDeathSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideOnDeathInferiors = new Hashtable(0);
    private static final Map allBaseAfterOnDeathSuperiors = new Hashtable(0);
    private static final Map allBaseAfterOnDeathInferiors = new Hashtable(0);
    private static final List beforeOnLivingUpdateHookTypes = new LinkedList();
    private static final List overrideOnLivingUpdateHookTypes = new LinkedList();
    private static final List afterOnLivingUpdateHookTypes = new LinkedList();
    private final PlayerBase[] beforeOnLivingUpdateHooks;
    private final PlayerBase[] overrideOnLivingUpdateHooks;
    private final PlayerBase[] afterOnLivingUpdateHooks;
    public final boolean isOnLivingUpdateModded;
    private static final Map allBaseBeforeOnLivingUpdateSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeOnLivingUpdateInferiors = new Hashtable(0);
    private static final Map allBaseOverrideOnLivingUpdateSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideOnLivingUpdateInferiors = new Hashtable(0);
    private static final Map allBaseAfterOnLivingUpdateSuperiors = new Hashtable(0);
    private static final Map allBaseAfterOnLivingUpdateInferiors = new Hashtable(0);
    private static final List beforeOnKillEntityHookTypes = new LinkedList();
    private static final List overrideOnKillEntityHookTypes = new LinkedList();
    private static final List afterOnKillEntityHookTypes = new LinkedList();
    private final PlayerBase[] beforeOnKillEntityHooks;
    private final PlayerBase[] overrideOnKillEntityHooks;
    private final PlayerBase[] afterOnKillEntityHooks;
    public final boolean isOnKillEntityModded;
    private static final Map allBaseBeforeOnKillEntitySuperiors = new Hashtable(0);
    private static final Map allBaseBeforeOnKillEntityInferiors = new Hashtable(0);
    private static final Map allBaseOverrideOnKillEntitySuperiors = new Hashtable(0);
    private static final Map allBaseOverrideOnKillEntityInferiors = new Hashtable(0);
    private static final Map allBaseAfterOnKillEntitySuperiors = new Hashtable(0);
    private static final Map allBaseAfterOnKillEntityInferiors = new Hashtable(0);
    private static final List beforeOnStruckByLightningHookTypes = new LinkedList();
    private static final List overrideOnStruckByLightningHookTypes = new LinkedList();
    private static final List afterOnStruckByLightningHookTypes = new LinkedList();
    private final PlayerBase[] beforeOnStruckByLightningHooks;
    private final PlayerBase[] overrideOnStruckByLightningHooks;
    private final PlayerBase[] afterOnStruckByLightningHooks;
    public final boolean isOnStruckByLightningModded;
    private static final Map allBaseBeforeOnStruckByLightningSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeOnStruckByLightningInferiors = new Hashtable(0);
    private static final Map allBaseOverrideOnStruckByLightningSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideOnStruckByLightningInferiors = new Hashtable(0);
    private static final Map allBaseAfterOnStruckByLightningSuperiors = new Hashtable(0);
    private static final Map allBaseAfterOnStruckByLightningInferiors = new Hashtable(0);
    private static final List beforeOnUpdateHookTypes = new LinkedList();
    private static final List overrideOnUpdateHookTypes = new LinkedList();
    private static final List afterOnUpdateHookTypes = new LinkedList();
    private final PlayerBase[] beforeOnUpdateHooks;
    private final PlayerBase[] overrideOnUpdateHooks;
    private final PlayerBase[] afterOnUpdateHooks;
    public final boolean isOnUpdateModded;
    private static final Map allBaseBeforeOnUpdateSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeOnUpdateInferiors = new Hashtable(0);
    private static final Map allBaseOverrideOnUpdateSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideOnUpdateInferiors = new Hashtable(0);
    private static final Map allBaseAfterOnUpdateSuperiors = new Hashtable(0);
    private static final Map allBaseAfterOnUpdateInferiors = new Hashtable(0);
    private static final List beforePlayStepSoundHookTypes = new LinkedList();
    private static final List overridePlayStepSoundHookTypes = new LinkedList();
    private static final List afterPlayStepSoundHookTypes = new LinkedList();
    private final PlayerBase[] beforePlayStepSoundHooks;
    private final PlayerBase[] overridePlayStepSoundHooks;
    private final PlayerBase[] afterPlayStepSoundHooks;
    public final boolean isPlayStepSoundModded;
    private static final Map allBaseBeforePlayStepSoundSuperiors = new Hashtable(0);
    private static final Map allBaseBeforePlayStepSoundInferiors = new Hashtable(0);
    private static final Map allBaseOverridePlayStepSoundSuperiors = new Hashtable(0);
    private static final Map allBaseOverridePlayStepSoundInferiors = new Hashtable(0);
    private static final Map allBaseAfterPlayStepSoundSuperiors = new Hashtable(0);
    private static final Map allBaseAfterPlayStepSoundInferiors = new Hashtable(0);
    private static final List beforePushOutOfBlocksHookTypes = new LinkedList();
    private static final List overridePushOutOfBlocksHookTypes = new LinkedList();
    private static final List afterPushOutOfBlocksHookTypes = new LinkedList();
    private final PlayerBase[] beforePushOutOfBlocksHooks;
    private final PlayerBase[] overridePushOutOfBlocksHooks;
    private final PlayerBase[] afterPushOutOfBlocksHooks;
    public final boolean isPushOutOfBlocksModded;
    private static final Map allBaseBeforePushOutOfBlocksSuperiors = new Hashtable(0);
    private static final Map allBaseBeforePushOutOfBlocksInferiors = new Hashtable(0);
    private static final Map allBaseOverridePushOutOfBlocksSuperiors = new Hashtable(0);
    private static final Map allBaseOverridePushOutOfBlocksInferiors = new Hashtable(0);
    private static final Map allBaseAfterPushOutOfBlocksSuperiors = new Hashtable(0);
    private static final Map allBaseAfterPushOutOfBlocksInferiors = new Hashtable(0);
    private static final List beforeRayTraceHookTypes = new LinkedList();
    private static final List overrideRayTraceHookTypes = new LinkedList();
    private static final List afterRayTraceHookTypes = new LinkedList();
    private final PlayerBase[] beforeRayTraceHooks;
    private final PlayerBase[] overrideRayTraceHooks;
    private final PlayerBase[] afterRayTraceHooks;
    public final boolean isRayTraceModded;
    private static final Map allBaseBeforeRayTraceSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRayTraceInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRayTraceSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRayTraceInferiors = new Hashtable(0);
    private static final Map allBaseAfterRayTraceSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRayTraceInferiors = new Hashtable(0);
    private static final List beforeReadEntityFromNBTHookTypes = new LinkedList();
    private static final List overrideReadEntityFromNBTHookTypes = new LinkedList();
    private static final List afterReadEntityFromNBTHookTypes = new LinkedList();
    private final PlayerBase[] beforeReadEntityFromNBTHooks;
    private final PlayerBase[] overrideReadEntityFromNBTHooks;
    private final PlayerBase[] afterReadEntityFromNBTHooks;
    public final boolean isReadEntityFromNBTModded;
    private static final Map allBaseBeforeReadEntityFromNBTSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeReadEntityFromNBTInferiors = new Hashtable(0);
    private static final Map allBaseOverrideReadEntityFromNBTSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideReadEntityFromNBTInferiors = new Hashtable(0);
    private static final Map allBaseAfterReadEntityFromNBTSuperiors = new Hashtable(0);
    private static final Map allBaseAfterReadEntityFromNBTInferiors = new Hashtable(0);
    private static final List beforeRespawnPlayerHookTypes = new LinkedList();
    private static final List overrideRespawnPlayerHookTypes = new LinkedList();
    private static final List afterRespawnPlayerHookTypes = new LinkedList();
    private final PlayerBase[] beforeRespawnPlayerHooks;
    private final PlayerBase[] overrideRespawnPlayerHooks;
    private final PlayerBase[] afterRespawnPlayerHooks;
    public final boolean isRespawnPlayerModded;
    private static final Map allBaseBeforeRespawnPlayerSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRespawnPlayerInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRespawnPlayerSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRespawnPlayerInferiors = new Hashtable(0);
    private static final Map allBaseAfterRespawnPlayerSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRespawnPlayerInferiors = new Hashtable(0);
    private static final List beforeSetDeadHookTypes = new LinkedList();
    private static final List overrideSetDeadHookTypes = new LinkedList();
    private static final List afterSetDeadHookTypes = new LinkedList();
    private final PlayerBase[] beforeSetDeadHooks;
    private final PlayerBase[] overrideSetDeadHooks;
    private final PlayerBase[] afterSetDeadHooks;
    public final boolean isSetDeadModded;
    private static final Map allBaseBeforeSetDeadSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSetDeadInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSetDeadSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSetDeadInferiors = new Hashtable(0);
    private static final Map allBaseAfterSetDeadSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSetDeadInferiors = new Hashtable(0);
    private static final List beforeSetPositionAndRotationHookTypes = new LinkedList();
    private static final List overrideSetPositionAndRotationHookTypes = new LinkedList();
    private static final List afterSetPositionAndRotationHookTypes = new LinkedList();
    private final PlayerBase[] beforeSetPositionAndRotationHooks;
    private final PlayerBase[] overrideSetPositionAndRotationHooks;
    private final PlayerBase[] afterSetPositionAndRotationHooks;
    public final boolean isSetPositionAndRotationModded;
    private static final Map allBaseBeforeSetPositionAndRotationSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSetPositionAndRotationInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSetPositionAndRotationSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSetPositionAndRotationInferiors = new Hashtable(0);
    private static final Map allBaseAfterSetPositionAndRotationSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSetPositionAndRotationInferiors = new Hashtable(0);
    private static final List beforeSleepInBedAtHookTypes = new LinkedList();
    private static final List overrideSleepInBedAtHookTypes = new LinkedList();
    private static final List afterSleepInBedAtHookTypes = new LinkedList();
    private final PlayerBase[] beforeSleepInBedAtHooks;
    private final PlayerBase[] overrideSleepInBedAtHooks;
    private final PlayerBase[] afterSleepInBedAtHooks;
    public final boolean isSleepInBedAtModded;
    private static final Map allBaseBeforeSleepInBedAtSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSleepInBedAtInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSleepInBedAtSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSleepInBedAtInferiors = new Hashtable(0);
    private static final Map allBaseAfterSleepInBedAtSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSleepInBedAtInferiors = new Hashtable(0);
    private static final List beforeSwingItemHookTypes = new LinkedList();
    private static final List overrideSwingItemHookTypes = new LinkedList();
    private static final List afterSwingItemHookTypes = new LinkedList();
    private final PlayerBase[] beforeSwingItemHooks;
    private final PlayerBase[] overrideSwingItemHooks;
    private final PlayerBase[] afterSwingItemHooks;
    public final boolean isSwingItemModded;
    private static final Map allBaseBeforeSwingItemSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSwingItemInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSwingItemSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSwingItemInferiors = new Hashtable(0);
    private static final Map allBaseAfterSwingItemSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSwingItemInferiors = new Hashtable(0);
    private static final List beforeUpdateCloakHookTypes = new LinkedList();
    private static final List overrideUpdateCloakHookTypes = new LinkedList();
    private static final List afterUpdateCloakHookTypes = new LinkedList();
    private final PlayerBase[] beforeUpdateCloakHooks;
    private final PlayerBase[] overrideUpdateCloakHooks;
    private final PlayerBase[] afterUpdateCloakHooks;
    public final boolean isUpdateCloakModded;
    private static final Map allBaseBeforeUpdateCloakSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeUpdateCloakInferiors = new Hashtable(0);
    private static final Map allBaseOverrideUpdateCloakSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideUpdateCloakInferiors = new Hashtable(0);
    private static final Map allBaseAfterUpdateCloakSuperiors = new Hashtable(0);
    private static final Map allBaseAfterUpdateCloakInferiors = new Hashtable(0);
    private static final List beforeUpdateEntityActionStateHookTypes = new LinkedList();
    private static final List overrideUpdateEntityActionStateHookTypes = new LinkedList();
    private static final List afterUpdateEntityActionStateHookTypes = new LinkedList();
    private final PlayerBase[] beforeUpdateEntityActionStateHooks;
    private final PlayerBase[] overrideUpdateEntityActionStateHooks;
    private final PlayerBase[] afterUpdateEntityActionStateHooks;
    public final boolean isUpdateEntityActionStateModded;
    private static final Map allBaseBeforeUpdateEntityActionStateSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeUpdateEntityActionStateInferiors = new Hashtable(0);
    private static final Map allBaseOverrideUpdateEntityActionStateSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideUpdateEntityActionStateInferiors = new Hashtable(0);
    private static final Map allBaseAfterUpdateEntityActionStateSuperiors = new Hashtable(0);
    private static final Map allBaseAfterUpdateEntityActionStateInferiors = new Hashtable(0);
    private static final List beforeUpdateRiddenHookTypes = new LinkedList();
    private static final List overrideUpdateRiddenHookTypes = new LinkedList();
    private static final List afterUpdateRiddenHookTypes = new LinkedList();
    private final PlayerBase[] beforeUpdateRiddenHooks;
    private final PlayerBase[] overrideUpdateRiddenHooks;
    private final PlayerBase[] afterUpdateRiddenHooks;
    public final boolean isUpdateRiddenModded;
    private static final Map allBaseBeforeUpdateRiddenSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeUpdateRiddenInferiors = new Hashtable(0);
    private static final Map allBaseOverrideUpdateRiddenSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideUpdateRiddenInferiors = new Hashtable(0);
    private static final Map allBaseAfterUpdateRiddenSuperiors = new Hashtable(0);
    private static final Map allBaseAfterUpdateRiddenInferiors = new Hashtable(0);
    private static final List beforeWriteEntityToNBTHookTypes = new LinkedList();
    private static final List overrideWriteEntityToNBTHookTypes = new LinkedList();
    private static final List afterWriteEntityToNBTHookTypes = new LinkedList();
    private final PlayerBase[] beforeWriteEntityToNBTHooks;
    private final PlayerBase[] overrideWriteEntityToNBTHooks;
    private final PlayerBase[] afterWriteEntityToNBTHooks;
    public final boolean isWriteEntityToNBTModded;
    private static final Map allBaseBeforeWriteEntityToNBTSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeWriteEntityToNBTInferiors = new Hashtable(0);
    private static final Map allBaseOverrideWriteEntityToNBTSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideWriteEntityToNBTInferiors = new Hashtable(0);
    private static final Map allBaseAfterWriteEntityToNBTSuperiors = new Hashtable(0);
    private static final Map allBaseAfterWriteEntityToNBTInferiors = new Hashtable(0);
    protected final EntityPlayerSP player;
    private static final List beforeLocalConstructingHookTypes = new LinkedList();
    private static final List afterLocalConstructingHookTypes = new LinkedList();
    private final PlayerBase[] beforeLocalConstructingHooks;
    private final PlayerBase[] afterLocalConstructingHooks;
    private final Map allBaseObjects = new Hashtable();
    private final Set unmodifiableAllBaseIds;
    private static final Map allBaseConstructors = new Hashtable();
    private static final Set unmodifiableAllIds = Collections.unmodifiableSet(allBaseConstructors.keySet());
    private static final Map allBaseBeforeLocalConstructingSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeLocalConstructingInferiors = new Hashtable(0);
    private static final Map allBaseAfterLocalConstructingSuperiors = new Hashtable(0);
    private static final Map allBaseAfterLocalConstructingInferiors = new Hashtable(0);
    private static boolean initialized = false;

    private static void log(String var0)
    {
        System.out.println(var0);
        logger.fine(var0);
    }

    public static void register(String var0, Class var1)
    {
        register(var0, var1, (PlayerBaseSorting)null);
    }

    public static void register(String var0, Class var1, PlayerBaseSorting var2)
    {
        try
        {
            register(var1, var0, var2);
        }
        catch (RuntimeException var4)
        {
            if (var0 != null)
            {
                log("PlayerAPI: failed to register id \'" + var0 + "\'");
            }
            else
            {
                log("PlayerAPI: failed to register PlayerBase");
            }

            throw var4;
        }
    }

    private static void register(Class var0, String var1, PlayerBaseSorting var2)
    {
        if (!isCreated)
        {
            log("PlayerAPI 1.6 Created");
            isCreated = true;
        }

        if (var1 == null)
        {
            throw new NullPointerException("Argument \'id\' can not be null");
        }
        else if (var0 == null)
        {
            throw new NullPointerException("Argument \'baseClass\' can not be null");
        }
        else
        {
            Constructor var3 = (Constructor)allBaseConstructors.get(var1);

            if (var3 != null)
            {
                throw new IllegalArgumentException("The class \'" + var0.getName() + "\' can not be registered with the id \'" + var1 + "\' because the class \'" + var3.getDeclaringClass().getName() + "\' has allready been registered with the same id");
            }
            else
            {
                Constructor var4;

                try
                {
                    var4 = var0.getDeclaredConstructor(Classes);
                }
                catch (Throwable var8)
                {
                    try
                    {
                        var4 = var0.getDeclaredConstructor(Class);
                    }
                    catch (Throwable var7)
                    {
                        throw new IllegalArgumentException("Can not find necessary constructor with one argument of type \'" + PlayerAPI.class.getName() + "\' and eventually a second argument of type \'String\' in the class \'" + var0.getName() + "\'", var8);
                    }
                }

                allBaseConstructors.put(var1, var4);

                if (var2 != null)
                {
                    addSorting(var1, allBaseBeforeLocalConstructingSuperiors, var2.getBeforeLocalConstructingSuperiors());
                    addSorting(var1, allBaseBeforeLocalConstructingInferiors, var2.getBeforeLocalConstructingInferiors());
                    addSorting(var1, allBaseAfterLocalConstructingSuperiors, var2.getAfterLocalConstructingSuperiors());
                    addSorting(var1, allBaseAfterLocalConstructingInferiors, var2.getAfterLocalConstructingInferiors());
                    addSorting(var1, allBaseBeforeAddExhaustionSuperiors, var2.getBeforeAddExhaustionSuperiors());
                    addSorting(var1, allBaseBeforeAddExhaustionInferiors, var2.getBeforeAddExhaustionInferiors());
                    addSorting(var1, allBaseOverrideAddExhaustionSuperiors, var2.getOverrideAddExhaustionSuperiors());
                    addSorting(var1, allBaseOverrideAddExhaustionInferiors, var2.getOverrideAddExhaustionInferiors());
                    addSorting(var1, allBaseAfterAddExhaustionSuperiors, var2.getAfterAddExhaustionSuperiors());
                    addSorting(var1, allBaseAfterAddExhaustionInferiors, var2.getAfterAddExhaustionInferiors());
                    addSorting(var1, allBaseBeforeAddMovementStatSuperiors, var2.getBeforeAddMovementStatSuperiors());
                    addSorting(var1, allBaseBeforeAddMovementStatInferiors, var2.getBeforeAddMovementStatInferiors());
                    addSorting(var1, allBaseOverrideAddMovementStatSuperiors, var2.getOverrideAddMovementStatSuperiors());
                    addSorting(var1, allBaseOverrideAddMovementStatInferiors, var2.getOverrideAddMovementStatInferiors());
                    addSorting(var1, allBaseAfterAddMovementStatSuperiors, var2.getAfterAddMovementStatSuperiors());
                    addSorting(var1, allBaseAfterAddMovementStatInferiors, var2.getAfterAddMovementStatInferiors());
                    addSorting(var1, allBaseBeforeAddStatSuperiors, var2.getBeforeAddStatSuperiors());
                    addSorting(var1, allBaseBeforeAddStatInferiors, var2.getBeforeAddStatInferiors());
                    addSorting(var1, allBaseOverrideAddStatSuperiors, var2.getOverrideAddStatSuperiors());
                    addSorting(var1, allBaseOverrideAddStatInferiors, var2.getOverrideAddStatInferiors());
                    addSorting(var1, allBaseAfterAddStatSuperiors, var2.getAfterAddStatSuperiors());
                    addSorting(var1, allBaseAfterAddStatInferiors, var2.getAfterAddStatInferiors());
                    addSorting(var1, allBaseBeforeAttackEntityFromSuperiors, var2.getBeforeAttackEntityFromSuperiors());
                    addSorting(var1, allBaseBeforeAttackEntityFromInferiors, var2.getBeforeAttackEntityFromInferiors());
                    addSorting(var1, allBaseOverrideAttackEntityFromSuperiors, var2.getOverrideAttackEntityFromSuperiors());
                    addSorting(var1, allBaseOverrideAttackEntityFromInferiors, var2.getOverrideAttackEntityFromInferiors());
                    addSorting(var1, allBaseAfterAttackEntityFromSuperiors, var2.getAfterAttackEntityFromSuperiors());
                    addSorting(var1, allBaseAfterAttackEntityFromInferiors, var2.getAfterAttackEntityFromInferiors());
                    addSorting(var1, allBaseBeforeAlertWolvesSuperiors, var2.getBeforeAlertWolvesSuperiors());
                    addSorting(var1, allBaseBeforeAlertWolvesInferiors, var2.getBeforeAlertWolvesInferiors());
                    addSorting(var1, allBaseOverrideAlertWolvesSuperiors, var2.getOverrideAlertWolvesSuperiors());
                    addSorting(var1, allBaseOverrideAlertWolvesInferiors, var2.getOverrideAlertWolvesInferiors());
                    addSorting(var1, allBaseAfterAlertWolvesSuperiors, var2.getAfterAlertWolvesSuperiors());
                    addSorting(var1, allBaseAfterAlertWolvesInferiors, var2.getAfterAlertWolvesInferiors());
                    addSorting(var1, allBaseBeforeAttackTargetEntityWithCurrentItemSuperiors, var2.getBeforeAttackTargetEntityWithCurrentItemSuperiors());
                    addSorting(var1, allBaseBeforeAttackTargetEntityWithCurrentItemInferiors, var2.getBeforeAttackTargetEntityWithCurrentItemInferiors());
                    addSorting(var1, allBaseOverrideAttackTargetEntityWithCurrentItemSuperiors, var2.getOverrideAttackTargetEntityWithCurrentItemSuperiors());
                    addSorting(var1, allBaseOverrideAttackTargetEntityWithCurrentItemInferiors, var2.getOverrideAttackTargetEntityWithCurrentItemInferiors());
                    addSorting(var1, allBaseAfterAttackTargetEntityWithCurrentItemSuperiors, var2.getAfterAttackTargetEntityWithCurrentItemSuperiors());
                    addSorting(var1, allBaseAfterAttackTargetEntityWithCurrentItemInferiors, var2.getAfterAttackTargetEntityWithCurrentItemInferiors());
                    addSorting(var1, allBaseBeforeCanBreatheUnderwaterSuperiors, var2.getBeforeCanBreatheUnderwaterSuperiors());
                    addSorting(var1, allBaseBeforeCanBreatheUnderwaterInferiors, var2.getBeforeCanBreatheUnderwaterInferiors());
                    addSorting(var1, allBaseOverrideCanBreatheUnderwaterSuperiors, var2.getOverrideCanBreatheUnderwaterSuperiors());
                    addSorting(var1, allBaseOverrideCanBreatheUnderwaterInferiors, var2.getOverrideCanBreatheUnderwaterInferiors());
                    addSorting(var1, allBaseAfterCanBreatheUnderwaterSuperiors, var2.getAfterCanBreatheUnderwaterSuperiors());
                    addSorting(var1, allBaseAfterCanBreatheUnderwaterInferiors, var2.getAfterCanBreatheUnderwaterInferiors());
                    addSorting(var1, allBaseBeforeCanHarvestBlockSuperiors, var2.getBeforeCanHarvestBlockSuperiors());
                    addSorting(var1, allBaseBeforeCanHarvestBlockInferiors, var2.getBeforeCanHarvestBlockInferiors());
                    addSorting(var1, allBaseOverrideCanHarvestBlockSuperiors, var2.getOverrideCanHarvestBlockSuperiors());
                    addSorting(var1, allBaseOverrideCanHarvestBlockInferiors, var2.getOverrideCanHarvestBlockInferiors());
                    addSorting(var1, allBaseAfterCanHarvestBlockSuperiors, var2.getAfterCanHarvestBlockSuperiors());
                    addSorting(var1, allBaseAfterCanHarvestBlockInferiors, var2.getAfterCanHarvestBlockInferiors());
                    addSorting(var1, allBaseBeforeCanPlayerEditSuperiors, var2.getBeforeCanPlayerEditSuperiors());
                    addSorting(var1, allBaseBeforeCanPlayerEditInferiors, var2.getBeforeCanPlayerEditInferiors());
                    addSorting(var1, allBaseOverrideCanPlayerEditSuperiors, var2.getOverrideCanPlayerEditSuperiors());
                    addSorting(var1, allBaseOverrideCanPlayerEditInferiors, var2.getOverrideCanPlayerEditInferiors());
                    addSorting(var1, allBaseAfterCanPlayerEditSuperiors, var2.getAfterCanPlayerEditSuperiors());
                    addSorting(var1, allBaseAfterCanPlayerEditInferiors, var2.getAfterCanPlayerEditInferiors());
                    addSorting(var1, allBaseBeforeCanTriggerWalkingSuperiors, var2.getBeforeCanTriggerWalkingSuperiors());
                    addSorting(var1, allBaseBeforeCanTriggerWalkingInferiors, var2.getBeforeCanTriggerWalkingInferiors());
                    addSorting(var1, allBaseOverrideCanTriggerWalkingSuperiors, var2.getOverrideCanTriggerWalkingSuperiors());
                    addSorting(var1, allBaseOverrideCanTriggerWalkingInferiors, var2.getOverrideCanTriggerWalkingInferiors());
                    addSorting(var1, allBaseAfterCanTriggerWalkingSuperiors, var2.getAfterCanTriggerWalkingSuperiors());
                    addSorting(var1, allBaseAfterCanTriggerWalkingInferiors, var2.getAfterCanTriggerWalkingInferiors());
                    addSorting(var1, allBaseBeforeCloseScreenSuperiors, var2.getBeforeCloseScreenSuperiors());
                    addSorting(var1, allBaseBeforeCloseScreenInferiors, var2.getBeforeCloseScreenInferiors());
                    addSorting(var1, allBaseOverrideCloseScreenSuperiors, var2.getOverrideCloseScreenSuperiors());
                    addSorting(var1, allBaseOverrideCloseScreenInferiors, var2.getOverrideCloseScreenInferiors());
                    addSorting(var1, allBaseAfterCloseScreenSuperiors, var2.getAfterCloseScreenSuperiors());
                    addSorting(var1, allBaseAfterCloseScreenInferiors, var2.getAfterCloseScreenInferiors());
                    addSorting(var1, allBaseBeforeDamageEntitySuperiors, var2.getBeforeDamageEntitySuperiors());
                    addSorting(var1, allBaseBeforeDamageEntityInferiors, var2.getBeforeDamageEntityInferiors());
                    addSorting(var1, allBaseOverrideDamageEntitySuperiors, var2.getOverrideDamageEntitySuperiors());
                    addSorting(var1, allBaseOverrideDamageEntityInferiors, var2.getOverrideDamageEntityInferiors());
                    addSorting(var1, allBaseAfterDamageEntitySuperiors, var2.getAfterDamageEntitySuperiors());
                    addSorting(var1, allBaseAfterDamageEntityInferiors, var2.getAfterDamageEntityInferiors());
                    addSorting(var1, allBaseBeforeDisplayGUIBrewingStandSuperiors, var2.getBeforeDisplayGUIBrewingStandSuperiors());
                    addSorting(var1, allBaseBeforeDisplayGUIBrewingStandInferiors, var2.getBeforeDisplayGUIBrewingStandInferiors());
                    addSorting(var1, allBaseOverrideDisplayGUIBrewingStandSuperiors, var2.getOverrideDisplayGUIBrewingStandSuperiors());
                    addSorting(var1, allBaseOverrideDisplayGUIBrewingStandInferiors, var2.getOverrideDisplayGUIBrewingStandInferiors());
                    addSorting(var1, allBaseAfterDisplayGUIBrewingStandSuperiors, var2.getAfterDisplayGUIBrewingStandSuperiors());
                    addSorting(var1, allBaseAfterDisplayGUIBrewingStandInferiors, var2.getAfterDisplayGUIBrewingStandInferiors());
                    addSorting(var1, allBaseBeforeDisplayGUIChestSuperiors, var2.getBeforeDisplayGUIChestSuperiors());
                    addSorting(var1, allBaseBeforeDisplayGUIChestInferiors, var2.getBeforeDisplayGUIChestInferiors());
                    addSorting(var1, allBaseOverrideDisplayGUIChestSuperiors, var2.getOverrideDisplayGUIChestSuperiors());
                    addSorting(var1, allBaseOverrideDisplayGUIChestInferiors, var2.getOverrideDisplayGUIChestInferiors());
                    addSorting(var1, allBaseAfterDisplayGUIChestSuperiors, var2.getAfterDisplayGUIChestSuperiors());
                    addSorting(var1, allBaseAfterDisplayGUIChestInferiors, var2.getAfterDisplayGUIChestInferiors());
                    addSorting(var1, allBaseBeforeDisplayGUIDispenserSuperiors, var2.getBeforeDisplayGUIDispenserSuperiors());
                    addSorting(var1, allBaseBeforeDisplayGUIDispenserInferiors, var2.getBeforeDisplayGUIDispenserInferiors());
                    addSorting(var1, allBaseOverrideDisplayGUIDispenserSuperiors, var2.getOverrideDisplayGUIDispenserSuperiors());
                    addSorting(var1, allBaseOverrideDisplayGUIDispenserInferiors, var2.getOverrideDisplayGUIDispenserInferiors());
                    addSorting(var1, allBaseAfterDisplayGUIDispenserSuperiors, var2.getAfterDisplayGUIDispenserSuperiors());
                    addSorting(var1, allBaseAfterDisplayGUIDispenserInferiors, var2.getAfterDisplayGUIDispenserInferiors());
                    addSorting(var1, allBaseBeforeDisplayGUIEditSignSuperiors, var2.getBeforeDisplayGUIEditSignSuperiors());
                    addSorting(var1, allBaseBeforeDisplayGUIEditSignInferiors, var2.getBeforeDisplayGUIEditSignInferiors());
                    addSorting(var1, allBaseOverrideDisplayGUIEditSignSuperiors, var2.getOverrideDisplayGUIEditSignSuperiors());
                    addSorting(var1, allBaseOverrideDisplayGUIEditSignInferiors, var2.getOverrideDisplayGUIEditSignInferiors());
                    addSorting(var1, allBaseAfterDisplayGUIEditSignSuperiors, var2.getAfterDisplayGUIEditSignSuperiors());
                    addSorting(var1, allBaseAfterDisplayGUIEditSignInferiors, var2.getAfterDisplayGUIEditSignInferiors());
                    addSorting(var1, allBaseBeforeDisplayGUIEnchantmentSuperiors, var2.getBeforeDisplayGUIEnchantmentSuperiors());
                    addSorting(var1, allBaseBeforeDisplayGUIEnchantmentInferiors, var2.getBeforeDisplayGUIEnchantmentInferiors());
                    addSorting(var1, allBaseOverrideDisplayGUIEnchantmentSuperiors, var2.getOverrideDisplayGUIEnchantmentSuperiors());
                    addSorting(var1, allBaseOverrideDisplayGUIEnchantmentInferiors, var2.getOverrideDisplayGUIEnchantmentInferiors());
                    addSorting(var1, allBaseAfterDisplayGUIEnchantmentSuperiors, var2.getAfterDisplayGUIEnchantmentSuperiors());
                    addSorting(var1, allBaseAfterDisplayGUIEnchantmentInferiors, var2.getAfterDisplayGUIEnchantmentInferiors());
                    addSorting(var1, allBaseBeforeDisplayGUIFurnaceSuperiors, var2.getBeforeDisplayGUIFurnaceSuperiors());
                    addSorting(var1, allBaseBeforeDisplayGUIFurnaceInferiors, var2.getBeforeDisplayGUIFurnaceInferiors());
                    addSorting(var1, allBaseOverrideDisplayGUIFurnaceSuperiors, var2.getOverrideDisplayGUIFurnaceSuperiors());
                    addSorting(var1, allBaseOverrideDisplayGUIFurnaceInferiors, var2.getOverrideDisplayGUIFurnaceInferiors());
                    addSorting(var1, allBaseAfterDisplayGUIFurnaceSuperiors, var2.getAfterDisplayGUIFurnaceSuperiors());
                    addSorting(var1, allBaseAfterDisplayGUIFurnaceInferiors, var2.getAfterDisplayGUIFurnaceInferiors());
                    addSorting(var1, allBaseBeforeDisplayGUIWorkbenchSuperiors, var2.getBeforeDisplayGUIWorkbenchSuperiors());
                    addSorting(var1, allBaseBeforeDisplayGUIWorkbenchInferiors, var2.getBeforeDisplayGUIWorkbenchInferiors());
                    addSorting(var1, allBaseOverrideDisplayGUIWorkbenchSuperiors, var2.getOverrideDisplayGUIWorkbenchSuperiors());
                    addSorting(var1, allBaseOverrideDisplayGUIWorkbenchInferiors, var2.getOverrideDisplayGUIWorkbenchInferiors());
                    addSorting(var1, allBaseAfterDisplayGUIWorkbenchSuperiors, var2.getAfterDisplayGUIWorkbenchSuperiors());
                    addSorting(var1, allBaseAfterDisplayGUIWorkbenchInferiors, var2.getAfterDisplayGUIWorkbenchInferiors());
                    addSorting(var1, allBaseBeforeDropOneItemSuperiors, var2.getBeforeDropOneItemSuperiors());
                    addSorting(var1, allBaseBeforeDropOneItemInferiors, var2.getBeforeDropOneItemInferiors());
                    addSorting(var1, allBaseOverrideDropOneItemSuperiors, var2.getOverrideDropOneItemSuperiors());
                    addSorting(var1, allBaseOverrideDropOneItemInferiors, var2.getOverrideDropOneItemInferiors());
                    addSorting(var1, allBaseAfterDropOneItemSuperiors, var2.getAfterDropOneItemSuperiors());
                    addSorting(var1, allBaseAfterDropOneItemInferiors, var2.getAfterDropOneItemInferiors());
                    addSorting(var1, allBaseBeforeDropPlayerItemSuperiors, var2.getBeforeDropPlayerItemSuperiors());
                    addSorting(var1, allBaseBeforeDropPlayerItemInferiors, var2.getBeforeDropPlayerItemInferiors());
                    addSorting(var1, allBaseOverrideDropPlayerItemSuperiors, var2.getOverrideDropPlayerItemSuperiors());
                    addSorting(var1, allBaseOverrideDropPlayerItemInferiors, var2.getOverrideDropPlayerItemInferiors());
                    addSorting(var1, allBaseAfterDropPlayerItemSuperiors, var2.getAfterDropPlayerItemSuperiors());
                    addSorting(var1, allBaseAfterDropPlayerItemInferiors, var2.getAfterDropPlayerItemInferiors());
                    addSorting(var1, allBaseBeforeDropPlayerItemWithRandomChoiceSuperiors, var2.getBeforeDropPlayerItemWithRandomChoiceSuperiors());
                    addSorting(var1, allBaseBeforeDropPlayerItemWithRandomChoiceInferiors, var2.getBeforeDropPlayerItemWithRandomChoiceInferiors());
                    addSorting(var1, allBaseOverrideDropPlayerItemWithRandomChoiceSuperiors, var2.getOverrideDropPlayerItemWithRandomChoiceSuperiors());
                    addSorting(var1, allBaseOverrideDropPlayerItemWithRandomChoiceInferiors, var2.getOverrideDropPlayerItemWithRandomChoiceInferiors());
                    addSorting(var1, allBaseAfterDropPlayerItemWithRandomChoiceSuperiors, var2.getAfterDropPlayerItemWithRandomChoiceSuperiors());
                    addSorting(var1, allBaseAfterDropPlayerItemWithRandomChoiceInferiors, var2.getAfterDropPlayerItemWithRandomChoiceInferiors());
                    addSorting(var1, allBaseBeforeFallSuperiors, var2.getBeforeFallSuperiors());
                    addSorting(var1, allBaseBeforeFallInferiors, var2.getBeforeFallInferiors());
                    addSorting(var1, allBaseOverrideFallSuperiors, var2.getOverrideFallSuperiors());
                    addSorting(var1, allBaseOverrideFallInferiors, var2.getOverrideFallInferiors());
                    addSorting(var1, allBaseAfterFallSuperiors, var2.getAfterFallSuperiors());
                    addSorting(var1, allBaseAfterFallInferiors, var2.getAfterFallInferiors());
                    addSorting(var1, allBaseBeforeGetBrightnessSuperiors, var2.getBeforeGetBrightnessSuperiors());
                    addSorting(var1, allBaseBeforeGetBrightnessInferiors, var2.getBeforeGetBrightnessInferiors());
                    addSorting(var1, allBaseOverrideGetBrightnessSuperiors, var2.getOverrideGetBrightnessSuperiors());
                    addSorting(var1, allBaseOverrideGetBrightnessInferiors, var2.getOverrideGetBrightnessInferiors());
                    addSorting(var1, allBaseAfterGetBrightnessSuperiors, var2.getAfterGetBrightnessSuperiors());
                    addSorting(var1, allBaseAfterGetBrightnessInferiors, var2.getAfterGetBrightnessInferiors());
                    addSorting(var1, allBaseBeforeGetBrightnessForRenderSuperiors, var2.getBeforeGetBrightnessForRenderSuperiors());
                    addSorting(var1, allBaseBeforeGetBrightnessForRenderInferiors, var2.getBeforeGetBrightnessForRenderInferiors());
                    addSorting(var1, allBaseOverrideGetBrightnessForRenderSuperiors, var2.getOverrideGetBrightnessForRenderSuperiors());
                    addSorting(var1, allBaseOverrideGetBrightnessForRenderInferiors, var2.getOverrideGetBrightnessForRenderInferiors());
                    addSorting(var1, allBaseAfterGetBrightnessForRenderSuperiors, var2.getAfterGetBrightnessForRenderSuperiors());
                    addSorting(var1, allBaseAfterGetBrightnessForRenderInferiors, var2.getAfterGetBrightnessForRenderInferiors());
                    addSorting(var1, allBaseBeforeGetCurrentPlayerStrVsBlockSuperiors, var2.getBeforeGetCurrentPlayerStrVsBlockSuperiors());
                    addSorting(var1, allBaseBeforeGetCurrentPlayerStrVsBlockInferiors, var2.getBeforeGetCurrentPlayerStrVsBlockInferiors());
                    addSorting(var1, allBaseOverrideGetCurrentPlayerStrVsBlockSuperiors, var2.getOverrideGetCurrentPlayerStrVsBlockSuperiors());
                    addSorting(var1, allBaseOverrideGetCurrentPlayerStrVsBlockInferiors, var2.getOverrideGetCurrentPlayerStrVsBlockInferiors());
                    addSorting(var1, allBaseAfterGetCurrentPlayerStrVsBlockSuperiors, var2.getAfterGetCurrentPlayerStrVsBlockSuperiors());
                    addSorting(var1, allBaseAfterGetCurrentPlayerStrVsBlockInferiors, var2.getAfterGetCurrentPlayerStrVsBlockInferiors());
                    addSorting(var1, allBaseBeforeGetDistanceSqSuperiors, var2.getBeforeGetDistanceSqSuperiors());
                    addSorting(var1, allBaseBeforeGetDistanceSqInferiors, var2.getBeforeGetDistanceSqInferiors());
                    addSorting(var1, allBaseOverrideGetDistanceSqSuperiors, var2.getOverrideGetDistanceSqSuperiors());
                    addSorting(var1, allBaseOverrideGetDistanceSqInferiors, var2.getOverrideGetDistanceSqInferiors());
                    addSorting(var1, allBaseAfterGetDistanceSqSuperiors, var2.getAfterGetDistanceSqSuperiors());
                    addSorting(var1, allBaseAfterGetDistanceSqInferiors, var2.getAfterGetDistanceSqInferiors());
                    addSorting(var1, allBaseBeforeGetDistanceSqToEntitySuperiors, var2.getBeforeGetDistanceSqToEntitySuperiors());
                    addSorting(var1, allBaseBeforeGetDistanceSqToEntityInferiors, var2.getBeforeGetDistanceSqToEntityInferiors());
                    addSorting(var1, allBaseOverrideGetDistanceSqToEntitySuperiors, var2.getOverrideGetDistanceSqToEntitySuperiors());
                    addSorting(var1, allBaseOverrideGetDistanceSqToEntityInferiors, var2.getOverrideGetDistanceSqToEntityInferiors());
                    addSorting(var1, allBaseAfterGetDistanceSqToEntitySuperiors, var2.getAfterGetDistanceSqToEntitySuperiors());
                    addSorting(var1, allBaseAfterGetDistanceSqToEntityInferiors, var2.getAfterGetDistanceSqToEntityInferiors());
                    addSorting(var1, allBaseBeforeGetFOVMultiplierSuperiors, var2.getBeforeGetFOVMultiplierSuperiors());
                    addSorting(var1, allBaseBeforeGetFOVMultiplierInferiors, var2.getBeforeGetFOVMultiplierInferiors());
                    addSorting(var1, allBaseOverrideGetFOVMultiplierSuperiors, var2.getOverrideGetFOVMultiplierSuperiors());
                    addSorting(var1, allBaseOverrideGetFOVMultiplierInferiors, var2.getOverrideGetFOVMultiplierInferiors());
                    addSorting(var1, allBaseAfterGetFOVMultiplierSuperiors, var2.getAfterGetFOVMultiplierSuperiors());
                    addSorting(var1, allBaseAfterGetFOVMultiplierInferiors, var2.getAfterGetFOVMultiplierInferiors());
                    addSorting(var1, allBaseBeforeGetHurtSoundSuperiors, var2.getBeforeGetHurtSoundSuperiors());
                    addSorting(var1, allBaseBeforeGetHurtSoundInferiors, var2.getBeforeGetHurtSoundInferiors());
                    addSorting(var1, allBaseOverrideGetHurtSoundSuperiors, var2.getOverrideGetHurtSoundSuperiors());
                    addSorting(var1, allBaseOverrideGetHurtSoundInferiors, var2.getOverrideGetHurtSoundInferiors());
                    addSorting(var1, allBaseAfterGetHurtSoundSuperiors, var2.getAfterGetHurtSoundSuperiors());
                    addSorting(var1, allBaseAfterGetHurtSoundInferiors, var2.getAfterGetHurtSoundInferiors());
                    addSorting(var1, allBaseBeforeGetItemIconSuperiors, var2.getBeforeGetItemIconSuperiors());
                    addSorting(var1, allBaseBeforeGetItemIconInferiors, var2.getBeforeGetItemIconInferiors());
                    addSorting(var1, allBaseOverrideGetItemIconSuperiors, var2.getOverrideGetItemIconSuperiors());
                    addSorting(var1, allBaseOverrideGetItemIconInferiors, var2.getOverrideGetItemIconInferiors());
                    addSorting(var1, allBaseAfterGetItemIconSuperiors, var2.getAfterGetItemIconSuperiors());
                    addSorting(var1, allBaseAfterGetItemIconInferiors, var2.getAfterGetItemIconInferiors());
                    addSorting(var1, allBaseBeforeGetMaxHealthSuperiors, var2.getBeforeGetMaxHealthSuperiors());
                    addSorting(var1, allBaseBeforeGetMaxHealthInferiors, var2.getBeforeGetMaxHealthInferiors());
                    addSorting(var1, allBaseOverrideGetMaxHealthSuperiors, var2.getOverrideGetMaxHealthSuperiors());
                    addSorting(var1, allBaseOverrideGetMaxHealthInferiors, var2.getOverrideGetMaxHealthInferiors());
                    addSorting(var1, allBaseAfterGetMaxHealthSuperiors, var2.getAfterGetMaxHealthSuperiors());
                    addSorting(var1, allBaseAfterGetMaxHealthInferiors, var2.getAfterGetMaxHealthInferiors());
                    addSorting(var1, allBaseBeforeGetSleepTimerSuperiors, var2.getBeforeGetSleepTimerSuperiors());
                    addSorting(var1, allBaseBeforeGetSleepTimerInferiors, var2.getBeforeGetSleepTimerInferiors());
                    addSorting(var1, allBaseOverrideGetSleepTimerSuperiors, var2.getOverrideGetSleepTimerSuperiors());
                    addSorting(var1, allBaseOverrideGetSleepTimerInferiors, var2.getOverrideGetSleepTimerInferiors());
                    addSorting(var1, allBaseAfterGetSleepTimerSuperiors, var2.getAfterGetSleepTimerSuperiors());
                    addSorting(var1, allBaseAfterGetSleepTimerInferiors, var2.getAfterGetSleepTimerInferiors());
                    addSorting(var1, allBaseBeforeGetSpeedModifierSuperiors, var2.getBeforeGetSpeedModifierSuperiors());
                    addSorting(var1, allBaseBeforeGetSpeedModifierInferiors, var2.getBeforeGetSpeedModifierInferiors());
                    addSorting(var1, allBaseOverrideGetSpeedModifierSuperiors, var2.getOverrideGetSpeedModifierSuperiors());
                    addSorting(var1, allBaseOverrideGetSpeedModifierInferiors, var2.getOverrideGetSpeedModifierInferiors());
                    addSorting(var1, allBaseAfterGetSpeedModifierSuperiors, var2.getAfterGetSpeedModifierSuperiors());
                    addSorting(var1, allBaseAfterGetSpeedModifierInferiors, var2.getAfterGetSpeedModifierInferiors());
                    addSorting(var1, allBaseBeforeHandleLavaMovementSuperiors, var2.getBeforeHandleLavaMovementSuperiors());
                    addSorting(var1, allBaseBeforeHandleLavaMovementInferiors, var2.getBeforeHandleLavaMovementInferiors());
                    addSorting(var1, allBaseOverrideHandleLavaMovementSuperiors, var2.getOverrideHandleLavaMovementSuperiors());
                    addSorting(var1, allBaseOverrideHandleLavaMovementInferiors, var2.getOverrideHandleLavaMovementInferiors());
                    addSorting(var1, allBaseAfterHandleLavaMovementSuperiors, var2.getAfterHandleLavaMovementSuperiors());
                    addSorting(var1, allBaseAfterHandleLavaMovementInferiors, var2.getAfterHandleLavaMovementInferiors());
                    addSorting(var1, allBaseBeforeHandleWaterMovementSuperiors, var2.getBeforeHandleWaterMovementSuperiors());
                    addSorting(var1, allBaseBeforeHandleWaterMovementInferiors, var2.getBeforeHandleWaterMovementInferiors());
                    addSorting(var1, allBaseOverrideHandleWaterMovementSuperiors, var2.getOverrideHandleWaterMovementSuperiors());
                    addSorting(var1, allBaseOverrideHandleWaterMovementInferiors, var2.getOverrideHandleWaterMovementInferiors());
                    addSorting(var1, allBaseAfterHandleWaterMovementSuperiors, var2.getAfterHandleWaterMovementSuperiors());
                    addSorting(var1, allBaseAfterHandleWaterMovementInferiors, var2.getAfterHandleWaterMovementInferiors());
                    addSorting(var1, allBaseBeforeHealSuperiors, var2.getBeforeHealSuperiors());
                    addSorting(var1, allBaseBeforeHealInferiors, var2.getBeforeHealInferiors());
                    addSorting(var1, allBaseOverrideHealSuperiors, var2.getOverrideHealSuperiors());
                    addSorting(var1, allBaseOverrideHealInferiors, var2.getOverrideHealInferiors());
                    addSorting(var1, allBaseAfterHealSuperiors, var2.getAfterHealSuperiors());
                    addSorting(var1, allBaseAfterHealInferiors, var2.getAfterHealInferiors());
                    addSorting(var1, allBaseBeforeInteractSuperiors, var2.getBeforeInteractSuperiors());
                    addSorting(var1, allBaseBeforeInteractInferiors, var2.getBeforeInteractInferiors());
                    addSorting(var1, allBaseOverrideInteractSuperiors, var2.getOverrideInteractSuperiors());
                    addSorting(var1, allBaseOverrideInteractInferiors, var2.getOverrideInteractInferiors());
                    addSorting(var1, allBaseAfterInteractSuperiors, var2.getAfterInteractSuperiors());
                    addSorting(var1, allBaseAfterInteractInferiors, var2.getAfterInteractInferiors());
                    addSorting(var1, allBaseBeforeIsEntityInsideOpaqueBlockSuperiors, var2.getBeforeIsEntityInsideOpaqueBlockSuperiors());
                    addSorting(var1, allBaseBeforeIsEntityInsideOpaqueBlockInferiors, var2.getBeforeIsEntityInsideOpaqueBlockInferiors());
                    addSorting(var1, allBaseOverrideIsEntityInsideOpaqueBlockSuperiors, var2.getOverrideIsEntityInsideOpaqueBlockSuperiors());
                    addSorting(var1, allBaseOverrideIsEntityInsideOpaqueBlockInferiors, var2.getOverrideIsEntityInsideOpaqueBlockInferiors());
                    addSorting(var1, allBaseAfterIsEntityInsideOpaqueBlockSuperiors, var2.getAfterIsEntityInsideOpaqueBlockSuperiors());
                    addSorting(var1, allBaseAfterIsEntityInsideOpaqueBlockInferiors, var2.getAfterIsEntityInsideOpaqueBlockInferiors());
                    addSorting(var1, allBaseBeforeIsInWaterSuperiors, var2.getBeforeIsInWaterSuperiors());
                    addSorting(var1, allBaseBeforeIsInWaterInferiors, var2.getBeforeIsInWaterInferiors());
                    addSorting(var1, allBaseOverrideIsInWaterSuperiors, var2.getOverrideIsInWaterSuperiors());
                    addSorting(var1, allBaseOverrideIsInWaterInferiors, var2.getOverrideIsInWaterInferiors());
                    addSorting(var1, allBaseAfterIsInWaterSuperiors, var2.getAfterIsInWaterSuperiors());
                    addSorting(var1, allBaseAfterIsInWaterInferiors, var2.getAfterIsInWaterInferiors());
                    addSorting(var1, allBaseBeforeIsInsideOfMaterialSuperiors, var2.getBeforeIsInsideOfMaterialSuperiors());
                    addSorting(var1, allBaseBeforeIsInsideOfMaterialInferiors, var2.getBeforeIsInsideOfMaterialInferiors());
                    addSorting(var1, allBaseOverrideIsInsideOfMaterialSuperiors, var2.getOverrideIsInsideOfMaterialSuperiors());
                    addSorting(var1, allBaseOverrideIsInsideOfMaterialInferiors, var2.getOverrideIsInsideOfMaterialInferiors());
                    addSorting(var1, allBaseAfterIsInsideOfMaterialSuperiors, var2.getAfterIsInsideOfMaterialSuperiors());
                    addSorting(var1, allBaseAfterIsInsideOfMaterialInferiors, var2.getAfterIsInsideOfMaterialInferiors());
                    addSorting(var1, allBaseBeforeIsOnLadderSuperiors, var2.getBeforeIsOnLadderSuperiors());
                    addSorting(var1, allBaseBeforeIsOnLadderInferiors, var2.getBeforeIsOnLadderInferiors());
                    addSorting(var1, allBaseOverrideIsOnLadderSuperiors, var2.getOverrideIsOnLadderSuperiors());
                    addSorting(var1, allBaseOverrideIsOnLadderInferiors, var2.getOverrideIsOnLadderInferiors());
                    addSorting(var1, allBaseAfterIsOnLadderSuperiors, var2.getAfterIsOnLadderSuperiors());
                    addSorting(var1, allBaseAfterIsOnLadderInferiors, var2.getAfterIsOnLadderInferiors());
                    addSorting(var1, allBaseBeforeIsPlayerSleepingSuperiors, var2.getBeforeIsPlayerSleepingSuperiors());
                    addSorting(var1, allBaseBeforeIsPlayerSleepingInferiors, var2.getBeforeIsPlayerSleepingInferiors());
                    addSorting(var1, allBaseOverrideIsPlayerSleepingSuperiors, var2.getOverrideIsPlayerSleepingSuperiors());
                    addSorting(var1, allBaseOverrideIsPlayerSleepingInferiors, var2.getOverrideIsPlayerSleepingInferiors());
                    addSorting(var1, allBaseAfterIsPlayerSleepingSuperiors, var2.getAfterIsPlayerSleepingSuperiors());
                    addSorting(var1, allBaseAfterIsPlayerSleepingInferiors, var2.getAfterIsPlayerSleepingInferiors());
                    addSorting(var1, allBaseBeforeIsSneakingSuperiors, var2.getBeforeIsSneakingSuperiors());
                    addSorting(var1, allBaseBeforeIsSneakingInferiors, var2.getBeforeIsSneakingInferiors());
                    addSorting(var1, allBaseOverrideIsSneakingSuperiors, var2.getOverrideIsSneakingSuperiors());
                    addSorting(var1, allBaseOverrideIsSneakingInferiors, var2.getOverrideIsSneakingInferiors());
                    addSorting(var1, allBaseAfterIsSneakingSuperiors, var2.getAfterIsSneakingSuperiors());
                    addSorting(var1, allBaseAfterIsSneakingInferiors, var2.getAfterIsSneakingInferiors());
                    addSorting(var1, allBaseBeforeIsSprintingSuperiors, var2.getBeforeIsSprintingSuperiors());
                    addSorting(var1, allBaseBeforeIsSprintingInferiors, var2.getBeforeIsSprintingInferiors());
                    addSorting(var1, allBaseOverrideIsSprintingSuperiors, var2.getOverrideIsSprintingSuperiors());
                    addSorting(var1, allBaseOverrideIsSprintingInferiors, var2.getOverrideIsSprintingInferiors());
                    addSorting(var1, allBaseAfterIsSprintingSuperiors, var2.getAfterIsSprintingSuperiors());
                    addSorting(var1, allBaseAfterIsSprintingInferiors, var2.getAfterIsSprintingInferiors());
                    addSorting(var1, allBaseBeforeJumpSuperiors, var2.getBeforeJumpSuperiors());
                    addSorting(var1, allBaseBeforeJumpInferiors, var2.getBeforeJumpInferiors());
                    addSorting(var1, allBaseOverrideJumpSuperiors, var2.getOverrideJumpSuperiors());
                    addSorting(var1, allBaseOverrideJumpInferiors, var2.getOverrideJumpInferiors());
                    addSorting(var1, allBaseAfterJumpSuperiors, var2.getAfterJumpSuperiors());
                    addSorting(var1, allBaseAfterJumpInferiors, var2.getAfterJumpInferiors());
                    addSorting(var1, allBaseBeforeKnockBackSuperiors, var2.getBeforeKnockBackSuperiors());
                    addSorting(var1, allBaseBeforeKnockBackInferiors, var2.getBeforeKnockBackInferiors());
                    addSorting(var1, allBaseOverrideKnockBackSuperiors, var2.getOverrideKnockBackSuperiors());
                    addSorting(var1, allBaseOverrideKnockBackInferiors, var2.getOverrideKnockBackInferiors());
                    addSorting(var1, allBaseAfterKnockBackSuperiors, var2.getAfterKnockBackSuperiors());
                    addSorting(var1, allBaseAfterKnockBackInferiors, var2.getAfterKnockBackInferiors());
                    addSorting(var1, allBaseBeforeMoveEntitySuperiors, var2.getBeforeMoveEntitySuperiors());
                    addSorting(var1, allBaseBeforeMoveEntityInferiors, var2.getBeforeMoveEntityInferiors());
                    addSorting(var1, allBaseOverrideMoveEntitySuperiors, var2.getOverrideMoveEntitySuperiors());
                    addSorting(var1, allBaseOverrideMoveEntityInferiors, var2.getOverrideMoveEntityInferiors());
                    addSorting(var1, allBaseAfterMoveEntitySuperiors, var2.getAfterMoveEntitySuperiors());
                    addSorting(var1, allBaseAfterMoveEntityInferiors, var2.getAfterMoveEntityInferiors());
                    addSorting(var1, allBaseBeforeMoveEntityWithHeadingSuperiors, var2.getBeforeMoveEntityWithHeadingSuperiors());
                    addSorting(var1, allBaseBeforeMoveEntityWithHeadingInferiors, var2.getBeforeMoveEntityWithHeadingInferiors());
                    addSorting(var1, allBaseOverrideMoveEntityWithHeadingSuperiors, var2.getOverrideMoveEntityWithHeadingSuperiors());
                    addSorting(var1, allBaseOverrideMoveEntityWithHeadingInferiors, var2.getOverrideMoveEntityWithHeadingInferiors());
                    addSorting(var1, allBaseAfterMoveEntityWithHeadingSuperiors, var2.getAfterMoveEntityWithHeadingSuperiors());
                    addSorting(var1, allBaseAfterMoveEntityWithHeadingInferiors, var2.getAfterMoveEntityWithHeadingInferiors());
                    addSorting(var1, allBaseBeforeMoveFlyingSuperiors, var2.getBeforeMoveFlyingSuperiors());
                    addSorting(var1, allBaseBeforeMoveFlyingInferiors, var2.getBeforeMoveFlyingInferiors());
                    addSorting(var1, allBaseOverrideMoveFlyingSuperiors, var2.getOverrideMoveFlyingSuperiors());
                    addSorting(var1, allBaseOverrideMoveFlyingInferiors, var2.getOverrideMoveFlyingInferiors());
                    addSorting(var1, allBaseAfterMoveFlyingSuperiors, var2.getAfterMoveFlyingSuperiors());
                    addSorting(var1, allBaseAfterMoveFlyingInferiors, var2.getAfterMoveFlyingInferiors());
                    addSorting(var1, allBaseBeforeOnDeathSuperiors, var2.getBeforeOnDeathSuperiors());
                    addSorting(var1, allBaseBeforeOnDeathInferiors, var2.getBeforeOnDeathInferiors());
                    addSorting(var1, allBaseOverrideOnDeathSuperiors, var2.getOverrideOnDeathSuperiors());
                    addSorting(var1, allBaseOverrideOnDeathInferiors, var2.getOverrideOnDeathInferiors());
                    addSorting(var1, allBaseAfterOnDeathSuperiors, var2.getAfterOnDeathSuperiors());
                    addSorting(var1, allBaseAfterOnDeathInferiors, var2.getAfterOnDeathInferiors());
                    addSorting(var1, allBaseBeforeOnLivingUpdateSuperiors, var2.getBeforeOnLivingUpdateSuperiors());
                    addSorting(var1, allBaseBeforeOnLivingUpdateInferiors, var2.getBeforeOnLivingUpdateInferiors());
                    addSorting(var1, allBaseOverrideOnLivingUpdateSuperiors, var2.getOverrideOnLivingUpdateSuperiors());
                    addSorting(var1, allBaseOverrideOnLivingUpdateInferiors, var2.getOverrideOnLivingUpdateInferiors());
                    addSorting(var1, allBaseAfterOnLivingUpdateSuperiors, var2.getAfterOnLivingUpdateSuperiors());
                    addSorting(var1, allBaseAfterOnLivingUpdateInferiors, var2.getAfterOnLivingUpdateInferiors());
                    addSorting(var1, allBaseBeforeOnKillEntitySuperiors, var2.getBeforeOnKillEntitySuperiors());
                    addSorting(var1, allBaseBeforeOnKillEntityInferiors, var2.getBeforeOnKillEntityInferiors());
                    addSorting(var1, allBaseOverrideOnKillEntitySuperiors, var2.getOverrideOnKillEntitySuperiors());
                    addSorting(var1, allBaseOverrideOnKillEntityInferiors, var2.getOverrideOnKillEntityInferiors());
                    addSorting(var1, allBaseAfterOnKillEntitySuperiors, var2.getAfterOnKillEntitySuperiors());
                    addSorting(var1, allBaseAfterOnKillEntityInferiors, var2.getAfterOnKillEntityInferiors());
                    addSorting(var1, allBaseBeforeOnStruckByLightningSuperiors, var2.getBeforeOnStruckByLightningSuperiors());
                    addSorting(var1, allBaseBeforeOnStruckByLightningInferiors, var2.getBeforeOnStruckByLightningInferiors());
                    addSorting(var1, allBaseOverrideOnStruckByLightningSuperiors, var2.getOverrideOnStruckByLightningSuperiors());
                    addSorting(var1, allBaseOverrideOnStruckByLightningInferiors, var2.getOverrideOnStruckByLightningInferiors());
                    addSorting(var1, allBaseAfterOnStruckByLightningSuperiors, var2.getAfterOnStruckByLightningSuperiors());
                    addSorting(var1, allBaseAfterOnStruckByLightningInferiors, var2.getAfterOnStruckByLightningInferiors());
                    addSorting(var1, allBaseBeforeOnUpdateSuperiors, var2.getBeforeOnUpdateSuperiors());
                    addSorting(var1, allBaseBeforeOnUpdateInferiors, var2.getBeforeOnUpdateInferiors());
                    addSorting(var1, allBaseOverrideOnUpdateSuperiors, var2.getOverrideOnUpdateSuperiors());
                    addSorting(var1, allBaseOverrideOnUpdateInferiors, var2.getOverrideOnUpdateInferiors());
                    addSorting(var1, allBaseAfterOnUpdateSuperiors, var2.getAfterOnUpdateSuperiors());
                    addSorting(var1, allBaseAfterOnUpdateInferiors, var2.getAfterOnUpdateInferiors());
                    addSorting(var1, allBaseBeforePlayStepSoundSuperiors, var2.getBeforePlayStepSoundSuperiors());
                    addSorting(var1, allBaseBeforePlayStepSoundInferiors, var2.getBeforePlayStepSoundInferiors());
                    addSorting(var1, allBaseOverridePlayStepSoundSuperiors, var2.getOverridePlayStepSoundSuperiors());
                    addSorting(var1, allBaseOverridePlayStepSoundInferiors, var2.getOverridePlayStepSoundInferiors());
                    addSorting(var1, allBaseAfterPlayStepSoundSuperiors, var2.getAfterPlayStepSoundSuperiors());
                    addSorting(var1, allBaseAfterPlayStepSoundInferiors, var2.getAfterPlayStepSoundInferiors());
                    addSorting(var1, allBaseBeforePushOutOfBlocksSuperiors, var2.getBeforePushOutOfBlocksSuperiors());
                    addSorting(var1, allBaseBeforePushOutOfBlocksInferiors, var2.getBeforePushOutOfBlocksInferiors());
                    addSorting(var1, allBaseOverridePushOutOfBlocksSuperiors, var2.getOverridePushOutOfBlocksSuperiors());
                    addSorting(var1, allBaseOverridePushOutOfBlocksInferiors, var2.getOverridePushOutOfBlocksInferiors());
                    addSorting(var1, allBaseAfterPushOutOfBlocksSuperiors, var2.getAfterPushOutOfBlocksSuperiors());
                    addSorting(var1, allBaseAfterPushOutOfBlocksInferiors, var2.getAfterPushOutOfBlocksInferiors());
                    addSorting(var1, allBaseBeforeRayTraceSuperiors, var2.getBeforeRayTraceSuperiors());
                    addSorting(var1, allBaseBeforeRayTraceInferiors, var2.getBeforeRayTraceInferiors());
                    addSorting(var1, allBaseOverrideRayTraceSuperiors, var2.getOverrideRayTraceSuperiors());
                    addSorting(var1, allBaseOverrideRayTraceInferiors, var2.getOverrideRayTraceInferiors());
                    addSorting(var1, allBaseAfterRayTraceSuperiors, var2.getAfterRayTraceSuperiors());
                    addSorting(var1, allBaseAfterRayTraceInferiors, var2.getAfterRayTraceInferiors());
                    addSorting(var1, allBaseBeforeReadEntityFromNBTSuperiors, var2.getBeforeReadEntityFromNBTSuperiors());
                    addSorting(var1, allBaseBeforeReadEntityFromNBTInferiors, var2.getBeforeReadEntityFromNBTInferiors());
                    addSorting(var1, allBaseOverrideReadEntityFromNBTSuperiors, var2.getOverrideReadEntityFromNBTSuperiors());
                    addSorting(var1, allBaseOverrideReadEntityFromNBTInferiors, var2.getOverrideReadEntityFromNBTInferiors());
                    addSorting(var1, allBaseAfterReadEntityFromNBTSuperiors, var2.getAfterReadEntityFromNBTSuperiors());
                    addSorting(var1, allBaseAfterReadEntityFromNBTInferiors, var2.getAfterReadEntityFromNBTInferiors());
                    addSorting(var1, allBaseBeforeRespawnPlayerSuperiors, var2.getBeforeRespawnPlayerSuperiors());
                    addSorting(var1, allBaseBeforeRespawnPlayerInferiors, var2.getBeforeRespawnPlayerInferiors());
                    addSorting(var1, allBaseOverrideRespawnPlayerSuperiors, var2.getOverrideRespawnPlayerSuperiors());
                    addSorting(var1, allBaseOverrideRespawnPlayerInferiors, var2.getOverrideRespawnPlayerInferiors());
                    addSorting(var1, allBaseAfterRespawnPlayerSuperiors, var2.getAfterRespawnPlayerSuperiors());
                    addSorting(var1, allBaseAfterRespawnPlayerInferiors, var2.getAfterRespawnPlayerInferiors());
                    addSorting(var1, allBaseBeforeSetDeadSuperiors, var2.getBeforeSetDeadSuperiors());
                    addSorting(var1, allBaseBeforeSetDeadInferiors, var2.getBeforeSetDeadInferiors());
                    addSorting(var1, allBaseOverrideSetDeadSuperiors, var2.getOverrideSetDeadSuperiors());
                    addSorting(var1, allBaseOverrideSetDeadInferiors, var2.getOverrideSetDeadInferiors());
                    addSorting(var1, allBaseAfterSetDeadSuperiors, var2.getAfterSetDeadSuperiors());
                    addSorting(var1, allBaseAfterSetDeadInferiors, var2.getAfterSetDeadInferiors());
                    addSorting(var1, allBaseBeforeSetPositionAndRotationSuperiors, var2.getBeforeSetPositionAndRotationSuperiors());
                    addSorting(var1, allBaseBeforeSetPositionAndRotationInferiors, var2.getBeforeSetPositionAndRotationInferiors());
                    addSorting(var1, allBaseOverrideSetPositionAndRotationSuperiors, var2.getOverrideSetPositionAndRotationSuperiors());
                    addSorting(var1, allBaseOverrideSetPositionAndRotationInferiors, var2.getOverrideSetPositionAndRotationInferiors());
                    addSorting(var1, allBaseAfterSetPositionAndRotationSuperiors, var2.getAfterSetPositionAndRotationSuperiors());
                    addSorting(var1, allBaseAfterSetPositionAndRotationInferiors, var2.getAfterSetPositionAndRotationInferiors());
                    addSorting(var1, allBaseBeforeSleepInBedAtSuperiors, var2.getBeforeSleepInBedAtSuperiors());
                    addSorting(var1, allBaseBeforeSleepInBedAtInferiors, var2.getBeforeSleepInBedAtInferiors());
                    addSorting(var1, allBaseOverrideSleepInBedAtSuperiors, var2.getOverrideSleepInBedAtSuperiors());
                    addSorting(var1, allBaseOverrideSleepInBedAtInferiors, var2.getOverrideSleepInBedAtInferiors());
                    addSorting(var1, allBaseAfterSleepInBedAtSuperiors, var2.getAfterSleepInBedAtSuperiors());
                    addSorting(var1, allBaseAfterSleepInBedAtInferiors, var2.getAfterSleepInBedAtInferiors());
                    addSorting(var1, allBaseBeforeSwingItemSuperiors, var2.getBeforeSwingItemSuperiors());
                    addSorting(var1, allBaseBeforeSwingItemInferiors, var2.getBeforeSwingItemInferiors());
                    addSorting(var1, allBaseOverrideSwingItemSuperiors, var2.getOverrideSwingItemSuperiors());
                    addSorting(var1, allBaseOverrideSwingItemInferiors, var2.getOverrideSwingItemInferiors());
                    addSorting(var1, allBaseAfterSwingItemSuperiors, var2.getAfterSwingItemSuperiors());
                    addSorting(var1, allBaseAfterSwingItemInferiors, var2.getAfterSwingItemInferiors());
                    addSorting(var1, allBaseBeforeUpdateCloakSuperiors, var2.getBeforeUpdateCloakSuperiors());
                    addSorting(var1, allBaseBeforeUpdateCloakInferiors, var2.getBeforeUpdateCloakInferiors());
                    addSorting(var1, allBaseOverrideUpdateCloakSuperiors, var2.getOverrideUpdateCloakSuperiors());
                    addSorting(var1, allBaseOverrideUpdateCloakInferiors, var2.getOverrideUpdateCloakInferiors());
                    addSorting(var1, allBaseAfterUpdateCloakSuperiors, var2.getAfterUpdateCloakSuperiors());
                    addSorting(var1, allBaseAfterUpdateCloakInferiors, var2.getAfterUpdateCloakInferiors());
                    addSorting(var1, allBaseBeforeUpdateEntityActionStateSuperiors, var2.getBeforeUpdateEntityActionStateSuperiors());
                    addSorting(var1, allBaseBeforeUpdateEntityActionStateInferiors, var2.getBeforeUpdateEntityActionStateInferiors());
                    addSorting(var1, allBaseOverrideUpdateEntityActionStateSuperiors, var2.getOverrideUpdateEntityActionStateSuperiors());
                    addSorting(var1, allBaseOverrideUpdateEntityActionStateInferiors, var2.getOverrideUpdateEntityActionStateInferiors());
                    addSorting(var1, allBaseAfterUpdateEntityActionStateSuperiors, var2.getAfterUpdateEntityActionStateSuperiors());
                    addSorting(var1, allBaseAfterUpdateEntityActionStateInferiors, var2.getAfterUpdateEntityActionStateInferiors());
                    addSorting(var1, allBaseBeforeUpdateRiddenSuperiors, var2.getBeforeUpdateRiddenSuperiors());
                    addSorting(var1, allBaseBeforeUpdateRiddenInferiors, var2.getBeforeUpdateRiddenInferiors());
                    addSorting(var1, allBaseOverrideUpdateRiddenSuperiors, var2.getOverrideUpdateRiddenSuperiors());
                    addSorting(var1, allBaseOverrideUpdateRiddenInferiors, var2.getOverrideUpdateRiddenInferiors());
                    addSorting(var1, allBaseAfterUpdateRiddenSuperiors, var2.getAfterUpdateRiddenSuperiors());
                    addSorting(var1, allBaseAfterUpdateRiddenInferiors, var2.getAfterUpdateRiddenInferiors());
                    addSorting(var1, allBaseBeforeWriteEntityToNBTSuperiors, var2.getBeforeWriteEntityToNBTSuperiors());
                    addSorting(var1, allBaseBeforeWriteEntityToNBTInferiors, var2.getBeforeWriteEntityToNBTInferiors());
                    addSorting(var1, allBaseOverrideWriteEntityToNBTSuperiors, var2.getOverrideWriteEntityToNBTSuperiors());
                    addSorting(var1, allBaseOverrideWriteEntityToNBTInferiors, var2.getOverrideWriteEntityToNBTInferiors());
                    addSorting(var1, allBaseAfterWriteEntityToNBTSuperiors, var2.getAfterWriteEntityToNBTSuperiors());
                    addSorting(var1, allBaseAfterWriteEntityToNBTInferiors, var2.getAfterWriteEntityToNBTInferiors());
                }

                addMethod(var1, var0, beforeLocalConstructingHookTypes, "beforeLocalConstructing", new Class[] {Minecraft.class, World.class, Session.class, Integer.TYPE});
                addMethod(var1, var0, afterLocalConstructingHookTypes, "afterLocalConstructing", new Class[] {Minecraft.class, World.class, Session.class, Integer.TYPE});
                addMethod(var1, var0, beforeAddExhaustionHookTypes, "beforeAddExhaustion", new Class[] {Float.TYPE});
                addMethod(var1, var0, overrideAddExhaustionHookTypes, "addExhaustion", new Class[] {Float.TYPE});
                addMethod(var1, var0, afterAddExhaustionHookTypes, "afterAddExhaustion", new Class[] {Float.TYPE});
                addMethod(var1, var0, beforeAddMovementStatHookTypes, "beforeAddMovementStat", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, overrideAddMovementStatHookTypes, "addMovementStat", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, afterAddMovementStatHookTypes, "afterAddMovementStat", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, beforeAddStatHookTypes, "beforeAddStat", new Class[] {StatBase.class, Integer.TYPE});
                addMethod(var1, var0, overrideAddStatHookTypes, "addStat", new Class[] {StatBase.class, Integer.TYPE});
                addMethod(var1, var0, afterAddStatHookTypes, "afterAddStat", new Class[] {StatBase.class, Integer.TYPE});
                addMethod(var1, var0, beforeAttackEntityFromHookTypes, "beforeAttackEntityFrom", new Class[] {DamageSource.class, Integer.TYPE});
                addMethod(var1, var0, overrideAttackEntityFromHookTypes, "attackEntityFrom", new Class[] {DamageSource.class, Integer.TYPE});
                addMethod(var1, var0, afterAttackEntityFromHookTypes, "afterAttackEntityFrom", new Class[] {DamageSource.class, Integer.TYPE});
                addMethod(var1, var0, beforeAlertWolvesHookTypes, "beforeAlertWolves", new Class[] {EntityLiving.class, Boolean.TYPE});
                addMethod(var1, var0, overrideAlertWolvesHookTypes, "alertWolves", new Class[] {EntityLiving.class, Boolean.TYPE});
                addMethod(var1, var0, afterAlertWolvesHookTypes, "afterAlertWolves", new Class[] {EntityLiving.class, Boolean.TYPE});
                addMethod(var1, var0, beforeAttackTargetEntityWithCurrentItemHookTypes, "beforeAttackTargetEntityWithCurrentItem", new Class[] {Entity.class});
                addMethod(var1, var0, overrideAttackTargetEntityWithCurrentItemHookTypes, "attackTargetEntityWithCurrentItem", new Class[] {Entity.class});
                addMethod(var1, var0, afterAttackTargetEntityWithCurrentItemHookTypes, "afterAttackTargetEntityWithCurrentItem", new Class[] {Entity.class});
                addMethod(var1, var0, beforeCanBreatheUnderwaterHookTypes, "beforeCanBreatheUnderwater", new Class[0]);
                addMethod(var1, var0, overrideCanBreatheUnderwaterHookTypes, "canBreatheUnderwater", new Class[0]);
                addMethod(var1, var0, afterCanBreatheUnderwaterHookTypes, "afterCanBreatheUnderwater", new Class[0]);
                addMethod(var1, var0, beforeCanHarvestBlockHookTypes, "beforeCanHarvestBlock", new Class[] {Block.class});
                addMethod(var1, var0, overrideCanHarvestBlockHookTypes, "canHarvestBlock", new Class[] {Block.class});
                addMethod(var1, var0, afterCanHarvestBlockHookTypes, "afterCanHarvestBlock", new Class[] {Block.class});
                addMethod(var1, var0, beforeCanPlayerEditHookTypes, "beforeCanPlayerEdit", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, ItemStack.class});
                addMethod(var1, var0, overrideCanPlayerEditHookTypes, "canPlayerEdit", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, ItemStack.class});
                addMethod(var1, var0, afterCanPlayerEditHookTypes, "afterCanPlayerEdit", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, ItemStack.class});
                addMethod(var1, var0, beforeCanTriggerWalkingHookTypes, "beforeCanTriggerWalking", new Class[0]);
                addMethod(var1, var0, overrideCanTriggerWalkingHookTypes, "canTriggerWalking", new Class[0]);
                addMethod(var1, var0, afterCanTriggerWalkingHookTypes, "afterCanTriggerWalking", new Class[0]);
                addMethod(var1, var0, beforeCloseScreenHookTypes, "beforeCloseScreen", new Class[0]);
                addMethod(var1, var0, overrideCloseScreenHookTypes, "closeScreen", new Class[0]);
                addMethod(var1, var0, afterCloseScreenHookTypes, "afterCloseScreen", new Class[0]);
                addMethod(var1, var0, beforeDamageEntityHookTypes, "beforeDamageEntity", new Class[] {DamageSource.class, Integer.TYPE});
                addMethod(var1, var0, overrideDamageEntityHookTypes, "damageEntity", new Class[] {DamageSource.class, Integer.TYPE});
                addMethod(var1, var0, afterDamageEntityHookTypes, "afterDamageEntity", new Class[] {DamageSource.class, Integer.TYPE});
                addMethod(var1, var0, beforeDisplayGUIBrewingStandHookTypes, "beforeDisplayGUIBrewingStand", new Class[] {TileEntityBrewingStand.class});
                addMethod(var1, var0, overrideDisplayGUIBrewingStandHookTypes, "displayGUIBrewingStand", new Class[] {TileEntityBrewingStand.class});
                addMethod(var1, var0, afterDisplayGUIBrewingStandHookTypes, "afterDisplayGUIBrewingStand", new Class[] {TileEntityBrewingStand.class});
                addMethod(var1, var0, beforeDisplayGUIChestHookTypes, "beforeDisplayGUIChest", new Class[] {IInventory.class});
                addMethod(var1, var0, overrideDisplayGUIChestHookTypes, "displayGUIChest", new Class[] {IInventory.class});
                addMethod(var1, var0, afterDisplayGUIChestHookTypes, "afterDisplayGUIChest", new Class[] {IInventory.class});
                addMethod(var1, var0, beforeDisplayGUIDispenserHookTypes, "beforeDisplayGUIDispenser", new Class[] {TileEntityDispenser.class});
                addMethod(var1, var0, overrideDisplayGUIDispenserHookTypes, "displayGUIDispenser", new Class[] {TileEntityDispenser.class});
                addMethod(var1, var0, afterDisplayGUIDispenserHookTypes, "afterDisplayGUIDispenser", new Class[] {TileEntityDispenser.class});
                addMethod(var1, var0, beforeDisplayGUIEditSignHookTypes, "beforeDisplayGUIEditSign", new Class[] {TileEntity.class});
                addMethod(var1, var0, overrideDisplayGUIEditSignHookTypes, "displayGUIEditSign", new Class[] {TileEntity.class});
                addMethod(var1, var0, afterDisplayGUIEditSignHookTypes, "afterDisplayGUIEditSign", new Class[] {TileEntity.class});
                addMethod(var1, var0, beforeDisplayGUIEnchantmentHookTypes, "beforeDisplayGUIEnchantment", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE});
                addMethod(var1, var0, overrideDisplayGUIEnchantmentHookTypes, "displayGUIEnchantment", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE});
                addMethod(var1, var0, afterDisplayGUIEnchantmentHookTypes, "afterDisplayGUIEnchantment", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE});
                addMethod(var1, var0, beforeDisplayGUIFurnaceHookTypes, "beforeDisplayGUIFurnace", new Class[] {TileEntityFurnace.class});
                addMethod(var1, var0, overrideDisplayGUIFurnaceHookTypes, "displayGUIFurnace", new Class[] {TileEntityFurnace.class});
                addMethod(var1, var0, afterDisplayGUIFurnaceHookTypes, "afterDisplayGUIFurnace", new Class[] {TileEntityFurnace.class});
                addMethod(var1, var0, beforeDisplayGUIWorkbenchHookTypes, "beforeDisplayGUIWorkbench", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE});
                addMethod(var1, var0, overrideDisplayGUIWorkbenchHookTypes, "displayGUIWorkbench", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE});
                addMethod(var1, var0, afterDisplayGUIWorkbenchHookTypes, "afterDisplayGUIWorkbench", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE});
                addMethod(var1, var0, beforeDropOneItemHookTypes, "beforeDropOneItem", new Class[] {Boolean.TYPE});
                addMethod(var1, var0, overrideDropOneItemHookTypes, "dropOneItem", new Class[] {Boolean.TYPE});
                addMethod(var1, var0, afterDropOneItemHookTypes, "afterDropOneItem", new Class[] {Boolean.TYPE});
                addMethod(var1, var0, beforeDropPlayerItemHookTypes, "beforeDropPlayerItem", new Class[] {ItemStack.class});
                addMethod(var1, var0, overrideDropPlayerItemHookTypes, "dropPlayerItem", new Class[] {ItemStack.class});
                addMethod(var1, var0, afterDropPlayerItemHookTypes, "afterDropPlayerItem", new Class[] {ItemStack.class});
                addMethod(var1, var0, beforeDropPlayerItemWithRandomChoiceHookTypes, "beforeDropPlayerItemWithRandomChoice", new Class[] {ItemStack.class, Boolean.TYPE});
                addMethod(var1, var0, overrideDropPlayerItemWithRandomChoiceHookTypes, "dropPlayerItemWithRandomChoice", new Class[] {ItemStack.class, Boolean.TYPE});
                addMethod(var1, var0, afterDropPlayerItemWithRandomChoiceHookTypes, "afterDropPlayerItemWithRandomChoice", new Class[] {ItemStack.class, Boolean.TYPE});
                addMethod(var1, var0, beforeFallHookTypes, "beforeFall", new Class[] {Float.TYPE});
                addMethod(var1, var0, overrideFallHookTypes, "fall", new Class[] {Float.TYPE});
                addMethod(var1, var0, afterFallHookTypes, "afterFall", new Class[] {Float.TYPE});
                addMethod(var1, var0, beforeGetBrightnessHookTypes, "beforeGetBrightness", new Class[] {Float.TYPE});
                addMethod(var1, var0, overrideGetBrightnessHookTypes, "getBrightness", new Class[] {Float.TYPE});
                addMethod(var1, var0, afterGetBrightnessHookTypes, "afterGetBrightness", new Class[] {Float.TYPE});
                addMethod(var1, var0, beforeGetBrightnessForRenderHookTypes, "beforeGetBrightnessForRender", new Class[] {Float.TYPE});
                addMethod(var1, var0, overrideGetBrightnessForRenderHookTypes, "getBrightnessForRender", new Class[] {Float.TYPE});
                addMethod(var1, var0, afterGetBrightnessForRenderHookTypes, "afterGetBrightnessForRender", new Class[] {Float.TYPE});
                addMethod(var1, var0, beforeGetCurrentPlayerStrVsBlockHookTypes, "beforeGetCurrentPlayerStrVsBlock", new Class[] {Block.class});
                addMethod(var1, var0, overrideGetCurrentPlayerStrVsBlockHookTypes, "getCurrentPlayerStrVsBlock", new Class[] {Block.class});
                addMethod(var1, var0, afterGetCurrentPlayerStrVsBlockHookTypes, "afterGetCurrentPlayerStrVsBlock", new Class[] {Block.class});
                addMethod(var1, var0, beforeGetDistanceSqHookTypes, "beforeGetDistanceSq", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, overrideGetDistanceSqHookTypes, "getDistanceSq", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, afterGetDistanceSqHookTypes, "afterGetDistanceSq", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, beforeGetDistanceSqToEntityHookTypes, "beforeGetDistanceSqToEntity", new Class[] {Entity.class});
                addMethod(var1, var0, overrideGetDistanceSqToEntityHookTypes, "getDistanceSqToEntity", new Class[] {Entity.class});
                addMethod(var1, var0, afterGetDistanceSqToEntityHookTypes, "afterGetDistanceSqToEntity", new Class[] {Entity.class});
                addMethod(var1, var0, beforeGetFOVMultiplierHookTypes, "beforeGetFOVMultiplier", new Class[0]);
                addMethod(var1, var0, overrideGetFOVMultiplierHookTypes, "getFOVMultiplier", new Class[0]);
                addMethod(var1, var0, afterGetFOVMultiplierHookTypes, "afterGetFOVMultiplier", new Class[0]);
                addMethod(var1, var0, beforeGetHurtSoundHookTypes, "beforeGetHurtSound", new Class[0]);
                addMethod(var1, var0, overrideGetHurtSoundHookTypes, "getHurtSound", new Class[0]);
                addMethod(var1, var0, afterGetHurtSoundHookTypes, "afterGetHurtSound", new Class[0]);
                addMethod(var1, var0, beforeGetItemIconHookTypes, "beforeGetItemIcon", new Class[] {ItemStack.class, Integer.TYPE});
                addMethod(var1, var0, overrideGetItemIconHookTypes, "getItemIcon", new Class[] {ItemStack.class, Integer.TYPE});
                addMethod(var1, var0, afterGetItemIconHookTypes, "afterGetItemIcon", new Class[] {ItemStack.class, Integer.TYPE});
                addMethod(var1, var0, beforeGetMaxHealthHookTypes, "beforeGetMaxHealth", new Class[0]);
                addMethod(var1, var0, overrideGetMaxHealthHookTypes, "getMaxHealth", new Class[0]);
                addMethod(var1, var0, afterGetMaxHealthHookTypes, "afterGetMaxHealth", new Class[0]);
                addMethod(var1, var0, beforeGetSleepTimerHookTypes, "beforeGetSleepTimer", new Class[0]);
                addMethod(var1, var0, overrideGetSleepTimerHookTypes, "getSleepTimer", new Class[0]);
                addMethod(var1, var0, afterGetSleepTimerHookTypes, "afterGetSleepTimer", new Class[0]);
                addMethod(var1, var0, beforeGetSpeedModifierHookTypes, "beforeGetSpeedModifier", new Class[0]);
                addMethod(var1, var0, overrideGetSpeedModifierHookTypes, "getSpeedModifier", new Class[0]);
                addMethod(var1, var0, afterGetSpeedModifierHookTypes, "afterGetSpeedModifier", new Class[0]);
                addMethod(var1, var0, beforeHandleLavaMovementHookTypes, "beforeHandleLavaMovement", new Class[0]);
                addMethod(var1, var0, overrideHandleLavaMovementHookTypes, "handleLavaMovement", new Class[0]);
                addMethod(var1, var0, afterHandleLavaMovementHookTypes, "afterHandleLavaMovement", new Class[0]);
                addMethod(var1, var0, beforeHandleWaterMovementHookTypes, "beforeHandleWaterMovement", new Class[0]);
                addMethod(var1, var0, overrideHandleWaterMovementHookTypes, "handleWaterMovement", new Class[0]);
                addMethod(var1, var0, afterHandleWaterMovementHookTypes, "afterHandleWaterMovement", new Class[0]);
                addMethod(var1, var0, beforeHealHookTypes, "beforeHeal", new Class[] {Integer.TYPE});
                addMethod(var1, var0, overrideHealHookTypes, "heal", new Class[] {Integer.TYPE});
                addMethod(var1, var0, afterHealHookTypes, "afterHeal", new Class[] {Integer.TYPE});
                addMethod(var1, var0, beforeInteractHookTypes, "beforeInteract", new Class[] {EntityPlayer.class});
                addMethod(var1, var0, overrideInteractHookTypes, "interact", new Class[] {EntityPlayer.class});
                addMethod(var1, var0, afterInteractHookTypes, "afterInteract", new Class[] {EntityPlayer.class});
                addMethod(var1, var0, beforeIsEntityInsideOpaqueBlockHookTypes, "beforeIsEntityInsideOpaqueBlock", new Class[0]);
                addMethod(var1, var0, overrideIsEntityInsideOpaqueBlockHookTypes, "isEntityInsideOpaqueBlock", new Class[0]);
                addMethod(var1, var0, afterIsEntityInsideOpaqueBlockHookTypes, "afterIsEntityInsideOpaqueBlock", new Class[0]);
                addMethod(var1, var0, beforeIsInWaterHookTypes, "beforeIsInWater", new Class[0]);
                addMethod(var1, var0, overrideIsInWaterHookTypes, "isInWater", new Class[0]);
                addMethod(var1, var0, afterIsInWaterHookTypes, "afterIsInWater", new Class[0]);
                addMethod(var1, var0, beforeIsInsideOfMaterialHookTypes, "beforeIsInsideOfMaterial", new Class[] {Material.class});
                addMethod(var1, var0, overrideIsInsideOfMaterialHookTypes, "isInsideOfMaterial", new Class[] {Material.class});
                addMethod(var1, var0, afterIsInsideOfMaterialHookTypes, "afterIsInsideOfMaterial", new Class[] {Material.class});
                addMethod(var1, var0, beforeIsOnLadderHookTypes, "beforeIsOnLadder", new Class[0]);
                addMethod(var1, var0, overrideIsOnLadderHookTypes, "isOnLadder", new Class[0]);
                addMethod(var1, var0, afterIsOnLadderHookTypes, "afterIsOnLadder", new Class[0]);
                addMethod(var1, var0, beforeIsPlayerSleepingHookTypes, "beforeIsPlayerSleeping", new Class[0]);
                addMethod(var1, var0, overrideIsPlayerSleepingHookTypes, "isPlayerSleeping", new Class[0]);
                addMethod(var1, var0, afterIsPlayerSleepingHookTypes, "afterIsPlayerSleeping", new Class[0]);
                addMethod(var1, var0, beforeIsSneakingHookTypes, "beforeIsSneaking", new Class[0]);
                addMethod(var1, var0, overrideIsSneakingHookTypes, "isSneaking", new Class[0]);
                addMethod(var1, var0, afterIsSneakingHookTypes, "afterIsSneaking", new Class[0]);
                addMethod(var1, var0, beforeIsSprintingHookTypes, "beforeIsSprinting", new Class[0]);
                addMethod(var1, var0, overrideIsSprintingHookTypes, "isSprinting", new Class[0]);
                addMethod(var1, var0, afterIsSprintingHookTypes, "afterIsSprinting", new Class[0]);
                addMethod(var1, var0, beforeJumpHookTypes, "beforeJump", new Class[0]);
                addMethod(var1, var0, overrideJumpHookTypes, "jump", new Class[0]);
                addMethod(var1, var0, afterJumpHookTypes, "afterJump", new Class[0]);
                addMethod(var1, var0, beforeKnockBackHookTypes, "beforeKnockBack", new Class[] {Entity.class, Integer.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, overrideKnockBackHookTypes, "knockBack", new Class[] {Entity.class, Integer.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, afterKnockBackHookTypes, "afterKnockBack", new Class[] {Entity.class, Integer.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, beforeMoveEntityHookTypes, "beforeMoveEntity", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, overrideMoveEntityHookTypes, "moveEntity", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, afterMoveEntityHookTypes, "afterMoveEntity", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, beforeMoveEntityWithHeadingHookTypes, "beforeMoveEntityWithHeading", new Class[] {Float.TYPE, Float.TYPE});
                addMethod(var1, var0, overrideMoveEntityWithHeadingHookTypes, "moveEntityWithHeading", new Class[] {Float.TYPE, Float.TYPE});
                addMethod(var1, var0, afterMoveEntityWithHeadingHookTypes, "afterMoveEntityWithHeading", new Class[] {Float.TYPE, Float.TYPE});
                addMethod(var1, var0, beforeMoveFlyingHookTypes, "beforeMoveFlying", new Class[] {Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, overrideMoveFlyingHookTypes, "moveFlying", new Class[] {Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, afterMoveFlyingHookTypes, "afterMoveFlying", new Class[] {Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, beforeOnDeathHookTypes, "beforeOnDeath", new Class[] {DamageSource.class});
                addMethod(var1, var0, overrideOnDeathHookTypes, "onDeath", new Class[] {DamageSource.class});
                addMethod(var1, var0, afterOnDeathHookTypes, "afterOnDeath", new Class[] {DamageSource.class});
                addMethod(var1, var0, beforeOnLivingUpdateHookTypes, "beforeOnLivingUpdate", new Class[0]);
                addMethod(var1, var0, overrideOnLivingUpdateHookTypes, "onLivingUpdate", new Class[0]);
                addMethod(var1, var0, afterOnLivingUpdateHookTypes, "afterOnLivingUpdate", new Class[0]);
                addMethod(var1, var0, beforeOnKillEntityHookTypes, "beforeOnKillEntity", new Class[] {EntityLiving.class});
                addMethod(var1, var0, overrideOnKillEntityHookTypes, "onKillEntity", new Class[] {EntityLiving.class});
                addMethod(var1, var0, afterOnKillEntityHookTypes, "afterOnKillEntity", new Class[] {EntityLiving.class});
                addMethod(var1, var0, beforeOnStruckByLightningHookTypes, "beforeOnStruckByLightning", new Class[] {EntityLightningBolt.class});
                addMethod(var1, var0, overrideOnStruckByLightningHookTypes, "onStruckByLightning", new Class[] {EntityLightningBolt.class});
                addMethod(var1, var0, afterOnStruckByLightningHookTypes, "afterOnStruckByLightning", new Class[] {EntityLightningBolt.class});
                addMethod(var1, var0, beforeOnUpdateHookTypes, "beforeOnUpdate", new Class[0]);
                addMethod(var1, var0, overrideOnUpdateHookTypes, "onUpdate", new Class[0]);
                addMethod(var1, var0, afterOnUpdateHookTypes, "afterOnUpdate", new Class[0]);
                addMethod(var1, var0, beforePlayStepSoundHookTypes, "beforePlayStepSound", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE});
                addMethod(var1, var0, overridePlayStepSoundHookTypes, "playStepSound", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE});
                addMethod(var1, var0, afterPlayStepSoundHookTypes, "afterPlayStepSound", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE});
                addMethod(var1, var0, beforePushOutOfBlocksHookTypes, "beforePushOutOfBlocks", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, overridePushOutOfBlocksHookTypes, "pushOutOfBlocks", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, afterPushOutOfBlocksHookTypes, "afterPushOutOfBlocks", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, beforeRayTraceHookTypes, "beforeRayTrace", new Class[] {Double.TYPE, Float.TYPE});
                addMethod(var1, var0, overrideRayTraceHookTypes, "rayTrace", new Class[] {Double.TYPE, Float.TYPE});
                addMethod(var1, var0, afterRayTraceHookTypes, "afterRayTrace", new Class[] {Double.TYPE, Float.TYPE});
                addMethod(var1, var0, beforeReadEntityFromNBTHookTypes, "beforeReadEntityFromNBT", new Class[] {NBTTagCompound.class});
                addMethod(var1, var0, overrideReadEntityFromNBTHookTypes, "readEntityFromNBT", new Class[] {NBTTagCompound.class});
                addMethod(var1, var0, afterReadEntityFromNBTHookTypes, "afterReadEntityFromNBT", new Class[] {NBTTagCompound.class});
                addMethod(var1, var0, beforeRespawnPlayerHookTypes, "beforeRespawnPlayer", new Class[0]);
                addMethod(var1, var0, overrideRespawnPlayerHookTypes, "respawnPlayer", new Class[0]);
                addMethod(var1, var0, afterRespawnPlayerHookTypes, "afterRespawnPlayer", new Class[0]);
                addMethod(var1, var0, beforeSetDeadHookTypes, "beforeSetDead", new Class[0]);
                addMethod(var1, var0, overrideSetDeadHookTypes, "setDead", new Class[0]);
                addMethod(var1, var0, afterSetDeadHookTypes, "afterSetDead", new Class[0]);
                addMethod(var1, var0, beforeSetPositionAndRotationHookTypes, "beforeSetPositionAndRotation", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, overrideSetPositionAndRotationHookTypes, "setPositionAndRotation", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, afterSetPositionAndRotationHookTypes, "afterSetPositionAndRotation", new Class[] {Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, beforeSleepInBedAtHookTypes, "beforeSleepInBedAt", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE});
                addMethod(var1, var0, overrideSleepInBedAtHookTypes, "sleepInBedAt", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE});
                addMethod(var1, var0, afterSleepInBedAtHookTypes, "afterSleepInBedAt", new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE});
                addMethod(var1, var0, beforeSwingItemHookTypes, "beforeSwingItem", new Class[0]);
                addMethod(var1, var0, overrideSwingItemHookTypes, "swingItem", new Class[0]);
                addMethod(var1, var0, afterSwingItemHookTypes, "afterSwingItem", new Class[0]);
                addMethod(var1, var0, beforeUpdateCloakHookTypes, "beforeUpdateCloak", new Class[0]);
                addMethod(var1, var0, overrideUpdateCloakHookTypes, "updateCloak", new Class[0]);
                addMethod(var1, var0, afterUpdateCloakHookTypes, "afterUpdateCloak", new Class[0]);
                addMethod(var1, var0, beforeUpdateEntityActionStateHookTypes, "beforeUpdateEntityActionState", new Class[0]);
                addMethod(var1, var0, overrideUpdateEntityActionStateHookTypes, "updateEntityActionState", new Class[0]);
                addMethod(var1, var0, afterUpdateEntityActionStateHookTypes, "afterUpdateEntityActionState", new Class[0]);
                addMethod(var1, var0, beforeUpdateRiddenHookTypes, "beforeUpdateRidden", new Class[0]);
                addMethod(var1, var0, overrideUpdateRiddenHookTypes, "updateRidden", new Class[0]);
                addMethod(var1, var0, afterUpdateRiddenHookTypes, "afterUpdateRidden", new Class[0]);
                addMethod(var1, var0, beforeWriteEntityToNBTHookTypes, "beforeWriteEntityToNBT", new Class[] {NBTTagCompound.class});
                addMethod(var1, var0, overrideWriteEntityToNBTHookTypes, "writeEntityToNBT", new Class[] {NBTTagCompound.class});
                addMethod(var1, var0, afterWriteEntityToNBTHookTypes, "afterWriteEntityToNBT", new Class[] {NBTTagCompound.class});
                System.out.println("PlayerAPI: registered " + var1);
                logger.fine("PlayerAPI: registered class \'" + var0.getName() + "\' with id \'" + var1 + "\'");
                initialized = false;
            }
        }
    }

    public static boolean unregister(String var0)
    {
        if (var0 != null && allBaseConstructors.remove(var0) != null)
        {
            beforeLocalConstructingHookTypes.remove(var0);
            afterLocalConstructingHookTypes.remove(var0);
            allBaseBeforeAddExhaustionSuperiors.remove(var0);
            allBaseBeforeAddExhaustionInferiors.remove(var0);
            allBaseOverrideAddExhaustionSuperiors.remove(var0);
            allBaseOverrideAddExhaustionInferiors.remove(var0);
            allBaseAfterAddExhaustionSuperiors.remove(var0);
            allBaseAfterAddExhaustionInferiors.remove(var0);
            beforeAddExhaustionHookTypes.remove(var0);
            overrideAddExhaustionHookTypes.remove(var0);
            afterAddExhaustionHookTypes.remove(var0);
            allBaseBeforeAddMovementStatSuperiors.remove(var0);
            allBaseBeforeAddMovementStatInferiors.remove(var0);
            allBaseOverrideAddMovementStatSuperiors.remove(var0);
            allBaseOverrideAddMovementStatInferiors.remove(var0);
            allBaseAfterAddMovementStatSuperiors.remove(var0);
            allBaseAfterAddMovementStatInferiors.remove(var0);
            beforeAddMovementStatHookTypes.remove(var0);
            overrideAddMovementStatHookTypes.remove(var0);
            afterAddMovementStatHookTypes.remove(var0);
            allBaseBeforeAddStatSuperiors.remove(var0);
            allBaseBeforeAddStatInferiors.remove(var0);
            allBaseOverrideAddStatSuperiors.remove(var0);
            allBaseOverrideAddStatInferiors.remove(var0);
            allBaseAfterAddStatSuperiors.remove(var0);
            allBaseAfterAddStatInferiors.remove(var0);
            beforeAddStatHookTypes.remove(var0);
            overrideAddStatHookTypes.remove(var0);
            afterAddStatHookTypes.remove(var0);
            allBaseBeforeAttackEntityFromSuperiors.remove(var0);
            allBaseBeforeAttackEntityFromInferiors.remove(var0);
            allBaseOverrideAttackEntityFromSuperiors.remove(var0);
            allBaseOverrideAttackEntityFromInferiors.remove(var0);
            allBaseAfterAttackEntityFromSuperiors.remove(var0);
            allBaseAfterAttackEntityFromInferiors.remove(var0);
            beforeAttackEntityFromHookTypes.remove(var0);
            overrideAttackEntityFromHookTypes.remove(var0);
            afterAttackEntityFromHookTypes.remove(var0);
            allBaseBeforeAlertWolvesSuperiors.remove(var0);
            allBaseBeforeAlertWolvesInferiors.remove(var0);
            allBaseOverrideAlertWolvesSuperiors.remove(var0);
            allBaseOverrideAlertWolvesInferiors.remove(var0);
            allBaseAfterAlertWolvesSuperiors.remove(var0);
            allBaseAfterAlertWolvesInferiors.remove(var0);
            beforeAlertWolvesHookTypes.remove(var0);
            overrideAlertWolvesHookTypes.remove(var0);
            afterAlertWolvesHookTypes.remove(var0);
            allBaseBeforeAttackTargetEntityWithCurrentItemSuperiors.remove(var0);
            allBaseBeforeAttackTargetEntityWithCurrentItemInferiors.remove(var0);
            allBaseOverrideAttackTargetEntityWithCurrentItemSuperiors.remove(var0);
            allBaseOverrideAttackTargetEntityWithCurrentItemInferiors.remove(var0);
            allBaseAfterAttackTargetEntityWithCurrentItemSuperiors.remove(var0);
            allBaseAfterAttackTargetEntityWithCurrentItemInferiors.remove(var0);
            beforeAttackTargetEntityWithCurrentItemHookTypes.remove(var0);
            overrideAttackTargetEntityWithCurrentItemHookTypes.remove(var0);
            afterAttackTargetEntityWithCurrentItemHookTypes.remove(var0);
            allBaseBeforeCanBreatheUnderwaterSuperiors.remove(var0);
            allBaseBeforeCanBreatheUnderwaterInferiors.remove(var0);
            allBaseOverrideCanBreatheUnderwaterSuperiors.remove(var0);
            allBaseOverrideCanBreatheUnderwaterInferiors.remove(var0);
            allBaseAfterCanBreatheUnderwaterSuperiors.remove(var0);
            allBaseAfterCanBreatheUnderwaterInferiors.remove(var0);
            beforeCanBreatheUnderwaterHookTypes.remove(var0);
            overrideCanBreatheUnderwaterHookTypes.remove(var0);
            afterCanBreatheUnderwaterHookTypes.remove(var0);
            allBaseBeforeCanHarvestBlockSuperiors.remove(var0);
            allBaseBeforeCanHarvestBlockInferiors.remove(var0);
            allBaseOverrideCanHarvestBlockSuperiors.remove(var0);
            allBaseOverrideCanHarvestBlockInferiors.remove(var0);
            allBaseAfterCanHarvestBlockSuperiors.remove(var0);
            allBaseAfterCanHarvestBlockInferiors.remove(var0);
            beforeCanHarvestBlockHookTypes.remove(var0);
            overrideCanHarvestBlockHookTypes.remove(var0);
            afterCanHarvestBlockHookTypes.remove(var0);
            allBaseBeforeCanPlayerEditSuperiors.remove(var0);
            allBaseBeforeCanPlayerEditInferiors.remove(var0);
            allBaseOverrideCanPlayerEditSuperiors.remove(var0);
            allBaseOverrideCanPlayerEditInferiors.remove(var0);
            allBaseAfterCanPlayerEditSuperiors.remove(var0);
            allBaseAfterCanPlayerEditInferiors.remove(var0);
            beforeCanPlayerEditHookTypes.remove(var0);
            overrideCanPlayerEditHookTypes.remove(var0);
            afterCanPlayerEditHookTypes.remove(var0);
            allBaseBeforeCanTriggerWalkingSuperiors.remove(var0);
            allBaseBeforeCanTriggerWalkingInferiors.remove(var0);
            allBaseOverrideCanTriggerWalkingSuperiors.remove(var0);
            allBaseOverrideCanTriggerWalkingInferiors.remove(var0);
            allBaseAfterCanTriggerWalkingSuperiors.remove(var0);
            allBaseAfterCanTriggerWalkingInferiors.remove(var0);
            beforeCanTriggerWalkingHookTypes.remove(var0);
            overrideCanTriggerWalkingHookTypes.remove(var0);
            afterCanTriggerWalkingHookTypes.remove(var0);
            allBaseBeforeCloseScreenSuperiors.remove(var0);
            allBaseBeforeCloseScreenInferiors.remove(var0);
            allBaseOverrideCloseScreenSuperiors.remove(var0);
            allBaseOverrideCloseScreenInferiors.remove(var0);
            allBaseAfterCloseScreenSuperiors.remove(var0);
            allBaseAfterCloseScreenInferiors.remove(var0);
            beforeCloseScreenHookTypes.remove(var0);
            overrideCloseScreenHookTypes.remove(var0);
            afterCloseScreenHookTypes.remove(var0);
            allBaseBeforeDamageEntitySuperiors.remove(var0);
            allBaseBeforeDamageEntityInferiors.remove(var0);
            allBaseOverrideDamageEntitySuperiors.remove(var0);
            allBaseOverrideDamageEntityInferiors.remove(var0);
            allBaseAfterDamageEntitySuperiors.remove(var0);
            allBaseAfterDamageEntityInferiors.remove(var0);
            beforeDamageEntityHookTypes.remove(var0);
            overrideDamageEntityHookTypes.remove(var0);
            afterDamageEntityHookTypes.remove(var0);
            allBaseBeforeDisplayGUIBrewingStandSuperiors.remove(var0);
            allBaseBeforeDisplayGUIBrewingStandInferiors.remove(var0);
            allBaseOverrideDisplayGUIBrewingStandSuperiors.remove(var0);
            allBaseOverrideDisplayGUIBrewingStandInferiors.remove(var0);
            allBaseAfterDisplayGUIBrewingStandSuperiors.remove(var0);
            allBaseAfterDisplayGUIBrewingStandInferiors.remove(var0);
            beforeDisplayGUIBrewingStandHookTypes.remove(var0);
            overrideDisplayGUIBrewingStandHookTypes.remove(var0);
            afterDisplayGUIBrewingStandHookTypes.remove(var0);
            allBaseBeforeDisplayGUIChestSuperiors.remove(var0);
            allBaseBeforeDisplayGUIChestInferiors.remove(var0);
            allBaseOverrideDisplayGUIChestSuperiors.remove(var0);
            allBaseOverrideDisplayGUIChestInferiors.remove(var0);
            allBaseAfterDisplayGUIChestSuperiors.remove(var0);
            allBaseAfterDisplayGUIChestInferiors.remove(var0);
            beforeDisplayGUIChestHookTypes.remove(var0);
            overrideDisplayGUIChestHookTypes.remove(var0);
            afterDisplayGUIChestHookTypes.remove(var0);
            allBaseBeforeDisplayGUIDispenserSuperiors.remove(var0);
            allBaseBeforeDisplayGUIDispenserInferiors.remove(var0);
            allBaseOverrideDisplayGUIDispenserSuperiors.remove(var0);
            allBaseOverrideDisplayGUIDispenserInferiors.remove(var0);
            allBaseAfterDisplayGUIDispenserSuperiors.remove(var0);
            allBaseAfterDisplayGUIDispenserInferiors.remove(var0);
            beforeDisplayGUIDispenserHookTypes.remove(var0);
            overrideDisplayGUIDispenserHookTypes.remove(var0);
            afterDisplayGUIDispenserHookTypes.remove(var0);
            allBaseBeforeDisplayGUIEditSignSuperiors.remove(var0);
            allBaseBeforeDisplayGUIEditSignInferiors.remove(var0);
            allBaseOverrideDisplayGUIEditSignSuperiors.remove(var0);
            allBaseOverrideDisplayGUIEditSignInferiors.remove(var0);
            allBaseAfterDisplayGUIEditSignSuperiors.remove(var0);
            allBaseAfterDisplayGUIEditSignInferiors.remove(var0);
            beforeDisplayGUIEditSignHookTypes.remove(var0);
            overrideDisplayGUIEditSignHookTypes.remove(var0);
            afterDisplayGUIEditSignHookTypes.remove(var0);
            allBaseBeforeDisplayGUIEnchantmentSuperiors.remove(var0);
            allBaseBeforeDisplayGUIEnchantmentInferiors.remove(var0);
            allBaseOverrideDisplayGUIEnchantmentSuperiors.remove(var0);
            allBaseOverrideDisplayGUIEnchantmentInferiors.remove(var0);
            allBaseAfterDisplayGUIEnchantmentSuperiors.remove(var0);
            allBaseAfterDisplayGUIEnchantmentInferiors.remove(var0);
            beforeDisplayGUIEnchantmentHookTypes.remove(var0);
            overrideDisplayGUIEnchantmentHookTypes.remove(var0);
            afterDisplayGUIEnchantmentHookTypes.remove(var0);
            allBaseBeforeDisplayGUIFurnaceSuperiors.remove(var0);
            allBaseBeforeDisplayGUIFurnaceInferiors.remove(var0);
            allBaseOverrideDisplayGUIFurnaceSuperiors.remove(var0);
            allBaseOverrideDisplayGUIFurnaceInferiors.remove(var0);
            allBaseAfterDisplayGUIFurnaceSuperiors.remove(var0);
            allBaseAfterDisplayGUIFurnaceInferiors.remove(var0);
            beforeDisplayGUIFurnaceHookTypes.remove(var0);
            overrideDisplayGUIFurnaceHookTypes.remove(var0);
            afterDisplayGUIFurnaceHookTypes.remove(var0);
            allBaseBeforeDisplayGUIWorkbenchSuperiors.remove(var0);
            allBaseBeforeDisplayGUIWorkbenchInferiors.remove(var0);
            allBaseOverrideDisplayGUIWorkbenchSuperiors.remove(var0);
            allBaseOverrideDisplayGUIWorkbenchInferiors.remove(var0);
            allBaseAfterDisplayGUIWorkbenchSuperiors.remove(var0);
            allBaseAfterDisplayGUIWorkbenchInferiors.remove(var0);
            beforeDisplayGUIWorkbenchHookTypes.remove(var0);
            overrideDisplayGUIWorkbenchHookTypes.remove(var0);
            afterDisplayGUIWorkbenchHookTypes.remove(var0);
            allBaseBeforeDropOneItemSuperiors.remove(var0);
            allBaseBeforeDropOneItemInferiors.remove(var0);
            allBaseOverrideDropOneItemSuperiors.remove(var0);
            allBaseOverrideDropOneItemInferiors.remove(var0);
            allBaseAfterDropOneItemSuperiors.remove(var0);
            allBaseAfterDropOneItemInferiors.remove(var0);
            beforeDropOneItemHookTypes.remove(var0);
            overrideDropOneItemHookTypes.remove(var0);
            afterDropOneItemHookTypes.remove(var0);
            allBaseBeforeDropPlayerItemSuperiors.remove(var0);
            allBaseBeforeDropPlayerItemInferiors.remove(var0);
            allBaseOverrideDropPlayerItemSuperiors.remove(var0);
            allBaseOverrideDropPlayerItemInferiors.remove(var0);
            allBaseAfterDropPlayerItemSuperiors.remove(var0);
            allBaseAfterDropPlayerItemInferiors.remove(var0);
            beforeDropPlayerItemHookTypes.remove(var0);
            overrideDropPlayerItemHookTypes.remove(var0);
            afterDropPlayerItemHookTypes.remove(var0);
            allBaseBeforeDropPlayerItemWithRandomChoiceSuperiors.remove(var0);
            allBaseBeforeDropPlayerItemWithRandomChoiceInferiors.remove(var0);
            allBaseOverrideDropPlayerItemWithRandomChoiceSuperiors.remove(var0);
            allBaseOverrideDropPlayerItemWithRandomChoiceInferiors.remove(var0);
            allBaseAfterDropPlayerItemWithRandomChoiceSuperiors.remove(var0);
            allBaseAfterDropPlayerItemWithRandomChoiceInferiors.remove(var0);
            beforeDropPlayerItemWithRandomChoiceHookTypes.remove(var0);
            overrideDropPlayerItemWithRandomChoiceHookTypes.remove(var0);
            afterDropPlayerItemWithRandomChoiceHookTypes.remove(var0);
            allBaseBeforeFallSuperiors.remove(var0);
            allBaseBeforeFallInferiors.remove(var0);
            allBaseOverrideFallSuperiors.remove(var0);
            allBaseOverrideFallInferiors.remove(var0);
            allBaseAfterFallSuperiors.remove(var0);
            allBaseAfterFallInferiors.remove(var0);
            beforeFallHookTypes.remove(var0);
            overrideFallHookTypes.remove(var0);
            afterFallHookTypes.remove(var0);
            allBaseBeforeGetBrightnessSuperiors.remove(var0);
            allBaseBeforeGetBrightnessInferiors.remove(var0);
            allBaseOverrideGetBrightnessSuperiors.remove(var0);
            allBaseOverrideGetBrightnessInferiors.remove(var0);
            allBaseAfterGetBrightnessSuperiors.remove(var0);
            allBaseAfterGetBrightnessInferiors.remove(var0);
            beforeGetBrightnessHookTypes.remove(var0);
            overrideGetBrightnessHookTypes.remove(var0);
            afterGetBrightnessHookTypes.remove(var0);
            allBaseBeforeGetBrightnessForRenderSuperiors.remove(var0);
            allBaseBeforeGetBrightnessForRenderInferiors.remove(var0);
            allBaseOverrideGetBrightnessForRenderSuperiors.remove(var0);
            allBaseOverrideGetBrightnessForRenderInferiors.remove(var0);
            allBaseAfterGetBrightnessForRenderSuperiors.remove(var0);
            allBaseAfterGetBrightnessForRenderInferiors.remove(var0);
            beforeGetBrightnessForRenderHookTypes.remove(var0);
            overrideGetBrightnessForRenderHookTypes.remove(var0);
            afterGetBrightnessForRenderHookTypes.remove(var0);
            allBaseBeforeGetCurrentPlayerStrVsBlockSuperiors.remove(var0);
            allBaseBeforeGetCurrentPlayerStrVsBlockInferiors.remove(var0);
            allBaseOverrideGetCurrentPlayerStrVsBlockSuperiors.remove(var0);
            allBaseOverrideGetCurrentPlayerStrVsBlockInferiors.remove(var0);
            allBaseAfterGetCurrentPlayerStrVsBlockSuperiors.remove(var0);
            allBaseAfterGetCurrentPlayerStrVsBlockInferiors.remove(var0);
            beforeGetCurrentPlayerStrVsBlockHookTypes.remove(var0);
            overrideGetCurrentPlayerStrVsBlockHookTypes.remove(var0);
            afterGetCurrentPlayerStrVsBlockHookTypes.remove(var0);
            allBaseBeforeGetDistanceSqSuperiors.remove(var0);
            allBaseBeforeGetDistanceSqInferiors.remove(var0);
            allBaseOverrideGetDistanceSqSuperiors.remove(var0);
            allBaseOverrideGetDistanceSqInferiors.remove(var0);
            allBaseAfterGetDistanceSqSuperiors.remove(var0);
            allBaseAfterGetDistanceSqInferiors.remove(var0);
            beforeGetDistanceSqHookTypes.remove(var0);
            overrideGetDistanceSqHookTypes.remove(var0);
            afterGetDistanceSqHookTypes.remove(var0);
            allBaseBeforeGetDistanceSqToEntitySuperiors.remove(var0);
            allBaseBeforeGetDistanceSqToEntityInferiors.remove(var0);
            allBaseOverrideGetDistanceSqToEntitySuperiors.remove(var0);
            allBaseOverrideGetDistanceSqToEntityInferiors.remove(var0);
            allBaseAfterGetDistanceSqToEntitySuperiors.remove(var0);
            allBaseAfterGetDistanceSqToEntityInferiors.remove(var0);
            beforeGetDistanceSqToEntityHookTypes.remove(var0);
            overrideGetDistanceSqToEntityHookTypes.remove(var0);
            afterGetDistanceSqToEntityHookTypes.remove(var0);
            allBaseBeforeGetFOVMultiplierSuperiors.remove(var0);
            allBaseBeforeGetFOVMultiplierInferiors.remove(var0);
            allBaseOverrideGetFOVMultiplierSuperiors.remove(var0);
            allBaseOverrideGetFOVMultiplierInferiors.remove(var0);
            allBaseAfterGetFOVMultiplierSuperiors.remove(var0);
            allBaseAfterGetFOVMultiplierInferiors.remove(var0);
            beforeGetFOVMultiplierHookTypes.remove(var0);
            overrideGetFOVMultiplierHookTypes.remove(var0);
            afterGetFOVMultiplierHookTypes.remove(var0);
            allBaseBeforeGetHurtSoundSuperiors.remove(var0);
            allBaseBeforeGetHurtSoundInferiors.remove(var0);
            allBaseOverrideGetHurtSoundSuperiors.remove(var0);
            allBaseOverrideGetHurtSoundInferiors.remove(var0);
            allBaseAfterGetHurtSoundSuperiors.remove(var0);
            allBaseAfterGetHurtSoundInferiors.remove(var0);
            beforeGetHurtSoundHookTypes.remove(var0);
            overrideGetHurtSoundHookTypes.remove(var0);
            afterGetHurtSoundHookTypes.remove(var0);
            allBaseBeforeGetItemIconSuperiors.remove(var0);
            allBaseBeforeGetItemIconInferiors.remove(var0);
            allBaseOverrideGetItemIconSuperiors.remove(var0);
            allBaseOverrideGetItemIconInferiors.remove(var0);
            allBaseAfterGetItemIconSuperiors.remove(var0);
            allBaseAfterGetItemIconInferiors.remove(var0);
            beforeGetItemIconHookTypes.remove(var0);
            overrideGetItemIconHookTypes.remove(var0);
            afterGetItemIconHookTypes.remove(var0);
            allBaseBeforeGetMaxHealthSuperiors.remove(var0);
            allBaseBeforeGetMaxHealthInferiors.remove(var0);
            allBaseOverrideGetMaxHealthSuperiors.remove(var0);
            allBaseOverrideGetMaxHealthInferiors.remove(var0);
            allBaseAfterGetMaxHealthSuperiors.remove(var0);
            allBaseAfterGetMaxHealthInferiors.remove(var0);
            beforeGetMaxHealthHookTypes.remove(var0);
            overrideGetMaxHealthHookTypes.remove(var0);
            afterGetMaxHealthHookTypes.remove(var0);
            allBaseBeforeGetSleepTimerSuperiors.remove(var0);
            allBaseBeforeGetSleepTimerInferiors.remove(var0);
            allBaseOverrideGetSleepTimerSuperiors.remove(var0);
            allBaseOverrideGetSleepTimerInferiors.remove(var0);
            allBaseAfterGetSleepTimerSuperiors.remove(var0);
            allBaseAfterGetSleepTimerInferiors.remove(var0);
            beforeGetSleepTimerHookTypes.remove(var0);
            overrideGetSleepTimerHookTypes.remove(var0);
            afterGetSleepTimerHookTypes.remove(var0);
            allBaseBeforeGetSpeedModifierSuperiors.remove(var0);
            allBaseBeforeGetSpeedModifierInferiors.remove(var0);
            allBaseOverrideGetSpeedModifierSuperiors.remove(var0);
            allBaseOverrideGetSpeedModifierInferiors.remove(var0);
            allBaseAfterGetSpeedModifierSuperiors.remove(var0);
            allBaseAfterGetSpeedModifierInferiors.remove(var0);
            beforeGetSpeedModifierHookTypes.remove(var0);
            overrideGetSpeedModifierHookTypes.remove(var0);
            afterGetSpeedModifierHookTypes.remove(var0);
            allBaseBeforeHandleLavaMovementSuperiors.remove(var0);
            allBaseBeforeHandleLavaMovementInferiors.remove(var0);
            allBaseOverrideHandleLavaMovementSuperiors.remove(var0);
            allBaseOverrideHandleLavaMovementInferiors.remove(var0);
            allBaseAfterHandleLavaMovementSuperiors.remove(var0);
            allBaseAfterHandleLavaMovementInferiors.remove(var0);
            beforeHandleLavaMovementHookTypes.remove(var0);
            overrideHandleLavaMovementHookTypes.remove(var0);
            afterHandleLavaMovementHookTypes.remove(var0);
            allBaseBeforeHandleWaterMovementSuperiors.remove(var0);
            allBaseBeforeHandleWaterMovementInferiors.remove(var0);
            allBaseOverrideHandleWaterMovementSuperiors.remove(var0);
            allBaseOverrideHandleWaterMovementInferiors.remove(var0);
            allBaseAfterHandleWaterMovementSuperiors.remove(var0);
            allBaseAfterHandleWaterMovementInferiors.remove(var0);
            beforeHandleWaterMovementHookTypes.remove(var0);
            overrideHandleWaterMovementHookTypes.remove(var0);
            afterHandleWaterMovementHookTypes.remove(var0);
            allBaseBeforeHealSuperiors.remove(var0);
            allBaseBeforeHealInferiors.remove(var0);
            allBaseOverrideHealSuperiors.remove(var0);
            allBaseOverrideHealInferiors.remove(var0);
            allBaseAfterHealSuperiors.remove(var0);
            allBaseAfterHealInferiors.remove(var0);
            beforeHealHookTypes.remove(var0);
            overrideHealHookTypes.remove(var0);
            afterHealHookTypes.remove(var0);
            allBaseBeforeInteractSuperiors.remove(var0);
            allBaseBeforeInteractInferiors.remove(var0);
            allBaseOverrideInteractSuperiors.remove(var0);
            allBaseOverrideInteractInferiors.remove(var0);
            allBaseAfterInteractSuperiors.remove(var0);
            allBaseAfterInteractInferiors.remove(var0);
            beforeInteractHookTypes.remove(var0);
            overrideInteractHookTypes.remove(var0);
            afterInteractHookTypes.remove(var0);
            allBaseBeforeIsEntityInsideOpaqueBlockSuperiors.remove(var0);
            allBaseBeforeIsEntityInsideOpaqueBlockInferiors.remove(var0);
            allBaseOverrideIsEntityInsideOpaqueBlockSuperiors.remove(var0);
            allBaseOverrideIsEntityInsideOpaqueBlockInferiors.remove(var0);
            allBaseAfterIsEntityInsideOpaqueBlockSuperiors.remove(var0);
            allBaseAfterIsEntityInsideOpaqueBlockInferiors.remove(var0);
            beforeIsEntityInsideOpaqueBlockHookTypes.remove(var0);
            overrideIsEntityInsideOpaqueBlockHookTypes.remove(var0);
            afterIsEntityInsideOpaqueBlockHookTypes.remove(var0);
            allBaseBeforeIsInWaterSuperiors.remove(var0);
            allBaseBeforeIsInWaterInferiors.remove(var0);
            allBaseOverrideIsInWaterSuperiors.remove(var0);
            allBaseOverrideIsInWaterInferiors.remove(var0);
            allBaseAfterIsInWaterSuperiors.remove(var0);
            allBaseAfterIsInWaterInferiors.remove(var0);
            beforeIsInWaterHookTypes.remove(var0);
            overrideIsInWaterHookTypes.remove(var0);
            afterIsInWaterHookTypes.remove(var0);
            allBaseBeforeIsInsideOfMaterialSuperiors.remove(var0);
            allBaseBeforeIsInsideOfMaterialInferiors.remove(var0);
            allBaseOverrideIsInsideOfMaterialSuperiors.remove(var0);
            allBaseOverrideIsInsideOfMaterialInferiors.remove(var0);
            allBaseAfterIsInsideOfMaterialSuperiors.remove(var0);
            allBaseAfterIsInsideOfMaterialInferiors.remove(var0);
            beforeIsInsideOfMaterialHookTypes.remove(var0);
            overrideIsInsideOfMaterialHookTypes.remove(var0);
            afterIsInsideOfMaterialHookTypes.remove(var0);
            allBaseBeforeIsOnLadderSuperiors.remove(var0);
            allBaseBeforeIsOnLadderInferiors.remove(var0);
            allBaseOverrideIsOnLadderSuperiors.remove(var0);
            allBaseOverrideIsOnLadderInferiors.remove(var0);
            allBaseAfterIsOnLadderSuperiors.remove(var0);
            allBaseAfterIsOnLadderInferiors.remove(var0);
            beforeIsOnLadderHookTypes.remove(var0);
            overrideIsOnLadderHookTypes.remove(var0);
            afterIsOnLadderHookTypes.remove(var0);
            allBaseBeforeIsPlayerSleepingSuperiors.remove(var0);
            allBaseBeforeIsPlayerSleepingInferiors.remove(var0);
            allBaseOverrideIsPlayerSleepingSuperiors.remove(var0);
            allBaseOverrideIsPlayerSleepingInferiors.remove(var0);
            allBaseAfterIsPlayerSleepingSuperiors.remove(var0);
            allBaseAfterIsPlayerSleepingInferiors.remove(var0);
            beforeIsPlayerSleepingHookTypes.remove(var0);
            overrideIsPlayerSleepingHookTypes.remove(var0);
            afterIsPlayerSleepingHookTypes.remove(var0);
            allBaseBeforeIsSneakingSuperiors.remove(var0);
            allBaseBeforeIsSneakingInferiors.remove(var0);
            allBaseOverrideIsSneakingSuperiors.remove(var0);
            allBaseOverrideIsSneakingInferiors.remove(var0);
            allBaseAfterIsSneakingSuperiors.remove(var0);
            allBaseAfterIsSneakingInferiors.remove(var0);
            beforeIsSneakingHookTypes.remove(var0);
            overrideIsSneakingHookTypes.remove(var0);
            afterIsSneakingHookTypes.remove(var0);
            allBaseBeforeIsSprintingSuperiors.remove(var0);
            allBaseBeforeIsSprintingInferiors.remove(var0);
            allBaseOverrideIsSprintingSuperiors.remove(var0);
            allBaseOverrideIsSprintingInferiors.remove(var0);
            allBaseAfterIsSprintingSuperiors.remove(var0);
            allBaseAfterIsSprintingInferiors.remove(var0);
            beforeIsSprintingHookTypes.remove(var0);
            overrideIsSprintingHookTypes.remove(var0);
            afterIsSprintingHookTypes.remove(var0);
            allBaseBeforeJumpSuperiors.remove(var0);
            allBaseBeforeJumpInferiors.remove(var0);
            allBaseOverrideJumpSuperiors.remove(var0);
            allBaseOverrideJumpInferiors.remove(var0);
            allBaseAfterJumpSuperiors.remove(var0);
            allBaseAfterJumpInferiors.remove(var0);
            beforeJumpHookTypes.remove(var0);
            overrideJumpHookTypes.remove(var0);
            afterJumpHookTypes.remove(var0);
            allBaseBeforeKnockBackSuperiors.remove(var0);
            allBaseBeforeKnockBackInferiors.remove(var0);
            allBaseOverrideKnockBackSuperiors.remove(var0);
            allBaseOverrideKnockBackInferiors.remove(var0);
            allBaseAfterKnockBackSuperiors.remove(var0);
            allBaseAfterKnockBackInferiors.remove(var0);
            beforeKnockBackHookTypes.remove(var0);
            overrideKnockBackHookTypes.remove(var0);
            afterKnockBackHookTypes.remove(var0);
            allBaseBeforeMoveEntitySuperiors.remove(var0);
            allBaseBeforeMoveEntityInferiors.remove(var0);
            allBaseOverrideMoveEntitySuperiors.remove(var0);
            allBaseOverrideMoveEntityInferiors.remove(var0);
            allBaseAfterMoveEntitySuperiors.remove(var0);
            allBaseAfterMoveEntityInferiors.remove(var0);
            beforeMoveEntityHookTypes.remove(var0);
            overrideMoveEntityHookTypes.remove(var0);
            afterMoveEntityHookTypes.remove(var0);
            allBaseBeforeMoveEntityWithHeadingSuperiors.remove(var0);
            allBaseBeforeMoveEntityWithHeadingInferiors.remove(var0);
            allBaseOverrideMoveEntityWithHeadingSuperiors.remove(var0);
            allBaseOverrideMoveEntityWithHeadingInferiors.remove(var0);
            allBaseAfterMoveEntityWithHeadingSuperiors.remove(var0);
            allBaseAfterMoveEntityWithHeadingInferiors.remove(var0);
            beforeMoveEntityWithHeadingHookTypes.remove(var0);
            overrideMoveEntityWithHeadingHookTypes.remove(var0);
            afterMoveEntityWithHeadingHookTypes.remove(var0);
            allBaseBeforeMoveFlyingSuperiors.remove(var0);
            allBaseBeforeMoveFlyingInferiors.remove(var0);
            allBaseOverrideMoveFlyingSuperiors.remove(var0);
            allBaseOverrideMoveFlyingInferiors.remove(var0);
            allBaseAfterMoveFlyingSuperiors.remove(var0);
            allBaseAfterMoveFlyingInferiors.remove(var0);
            beforeMoveFlyingHookTypes.remove(var0);
            overrideMoveFlyingHookTypes.remove(var0);
            afterMoveFlyingHookTypes.remove(var0);
            allBaseBeforeOnDeathSuperiors.remove(var0);
            allBaseBeforeOnDeathInferiors.remove(var0);
            allBaseOverrideOnDeathSuperiors.remove(var0);
            allBaseOverrideOnDeathInferiors.remove(var0);
            allBaseAfterOnDeathSuperiors.remove(var0);
            allBaseAfterOnDeathInferiors.remove(var0);
            beforeOnDeathHookTypes.remove(var0);
            overrideOnDeathHookTypes.remove(var0);
            afterOnDeathHookTypes.remove(var0);
            allBaseBeforeOnLivingUpdateSuperiors.remove(var0);
            allBaseBeforeOnLivingUpdateInferiors.remove(var0);
            allBaseOverrideOnLivingUpdateSuperiors.remove(var0);
            allBaseOverrideOnLivingUpdateInferiors.remove(var0);
            allBaseAfterOnLivingUpdateSuperiors.remove(var0);
            allBaseAfterOnLivingUpdateInferiors.remove(var0);
            beforeOnLivingUpdateHookTypes.remove(var0);
            overrideOnLivingUpdateHookTypes.remove(var0);
            afterOnLivingUpdateHookTypes.remove(var0);
            allBaseBeforeOnKillEntitySuperiors.remove(var0);
            allBaseBeforeOnKillEntityInferiors.remove(var0);
            allBaseOverrideOnKillEntitySuperiors.remove(var0);
            allBaseOverrideOnKillEntityInferiors.remove(var0);
            allBaseAfterOnKillEntitySuperiors.remove(var0);
            allBaseAfterOnKillEntityInferiors.remove(var0);
            beforeOnKillEntityHookTypes.remove(var0);
            overrideOnKillEntityHookTypes.remove(var0);
            afterOnKillEntityHookTypes.remove(var0);
            allBaseBeforeOnStruckByLightningSuperiors.remove(var0);
            allBaseBeforeOnStruckByLightningInferiors.remove(var0);
            allBaseOverrideOnStruckByLightningSuperiors.remove(var0);
            allBaseOverrideOnStruckByLightningInferiors.remove(var0);
            allBaseAfterOnStruckByLightningSuperiors.remove(var0);
            allBaseAfterOnStruckByLightningInferiors.remove(var0);
            beforeOnStruckByLightningHookTypes.remove(var0);
            overrideOnStruckByLightningHookTypes.remove(var0);
            afterOnStruckByLightningHookTypes.remove(var0);
            allBaseBeforeOnUpdateSuperiors.remove(var0);
            allBaseBeforeOnUpdateInferiors.remove(var0);
            allBaseOverrideOnUpdateSuperiors.remove(var0);
            allBaseOverrideOnUpdateInferiors.remove(var0);
            allBaseAfterOnUpdateSuperiors.remove(var0);
            allBaseAfterOnUpdateInferiors.remove(var0);
            beforeOnUpdateHookTypes.remove(var0);
            overrideOnUpdateHookTypes.remove(var0);
            afterOnUpdateHookTypes.remove(var0);
            allBaseBeforePlayStepSoundSuperiors.remove(var0);
            allBaseBeforePlayStepSoundInferiors.remove(var0);
            allBaseOverridePlayStepSoundSuperiors.remove(var0);
            allBaseOverridePlayStepSoundInferiors.remove(var0);
            allBaseAfterPlayStepSoundSuperiors.remove(var0);
            allBaseAfterPlayStepSoundInferiors.remove(var0);
            beforePlayStepSoundHookTypes.remove(var0);
            overridePlayStepSoundHookTypes.remove(var0);
            afterPlayStepSoundHookTypes.remove(var0);
            allBaseBeforePushOutOfBlocksSuperiors.remove(var0);
            allBaseBeforePushOutOfBlocksInferiors.remove(var0);
            allBaseOverridePushOutOfBlocksSuperiors.remove(var0);
            allBaseOverridePushOutOfBlocksInferiors.remove(var0);
            allBaseAfterPushOutOfBlocksSuperiors.remove(var0);
            allBaseAfterPushOutOfBlocksInferiors.remove(var0);
            beforePushOutOfBlocksHookTypes.remove(var0);
            overridePushOutOfBlocksHookTypes.remove(var0);
            afterPushOutOfBlocksHookTypes.remove(var0);
            allBaseBeforeRayTraceSuperiors.remove(var0);
            allBaseBeforeRayTraceInferiors.remove(var0);
            allBaseOverrideRayTraceSuperiors.remove(var0);
            allBaseOverrideRayTraceInferiors.remove(var0);
            allBaseAfterRayTraceSuperiors.remove(var0);
            allBaseAfterRayTraceInferiors.remove(var0);
            beforeRayTraceHookTypes.remove(var0);
            overrideRayTraceHookTypes.remove(var0);
            afterRayTraceHookTypes.remove(var0);
            allBaseBeforeReadEntityFromNBTSuperiors.remove(var0);
            allBaseBeforeReadEntityFromNBTInferiors.remove(var0);
            allBaseOverrideReadEntityFromNBTSuperiors.remove(var0);
            allBaseOverrideReadEntityFromNBTInferiors.remove(var0);
            allBaseAfterReadEntityFromNBTSuperiors.remove(var0);
            allBaseAfterReadEntityFromNBTInferiors.remove(var0);
            beforeReadEntityFromNBTHookTypes.remove(var0);
            overrideReadEntityFromNBTHookTypes.remove(var0);
            afterReadEntityFromNBTHookTypes.remove(var0);
            allBaseBeforeRespawnPlayerSuperiors.remove(var0);
            allBaseBeforeRespawnPlayerInferiors.remove(var0);
            allBaseOverrideRespawnPlayerSuperiors.remove(var0);
            allBaseOverrideRespawnPlayerInferiors.remove(var0);
            allBaseAfterRespawnPlayerSuperiors.remove(var0);
            allBaseAfterRespawnPlayerInferiors.remove(var0);
            beforeRespawnPlayerHookTypes.remove(var0);
            overrideRespawnPlayerHookTypes.remove(var0);
            afterRespawnPlayerHookTypes.remove(var0);
            allBaseBeforeSetDeadSuperiors.remove(var0);
            allBaseBeforeSetDeadInferiors.remove(var0);
            allBaseOverrideSetDeadSuperiors.remove(var0);
            allBaseOverrideSetDeadInferiors.remove(var0);
            allBaseAfterSetDeadSuperiors.remove(var0);
            allBaseAfterSetDeadInferiors.remove(var0);
            beforeSetDeadHookTypes.remove(var0);
            overrideSetDeadHookTypes.remove(var0);
            afterSetDeadHookTypes.remove(var0);
            allBaseBeforeSetPositionAndRotationSuperiors.remove(var0);
            allBaseBeforeSetPositionAndRotationInferiors.remove(var0);
            allBaseOverrideSetPositionAndRotationSuperiors.remove(var0);
            allBaseOverrideSetPositionAndRotationInferiors.remove(var0);
            allBaseAfterSetPositionAndRotationSuperiors.remove(var0);
            allBaseAfterSetPositionAndRotationInferiors.remove(var0);
            beforeSetPositionAndRotationHookTypes.remove(var0);
            overrideSetPositionAndRotationHookTypes.remove(var0);
            afterSetPositionAndRotationHookTypes.remove(var0);
            allBaseBeforeSleepInBedAtSuperiors.remove(var0);
            allBaseBeforeSleepInBedAtInferiors.remove(var0);
            allBaseOverrideSleepInBedAtSuperiors.remove(var0);
            allBaseOverrideSleepInBedAtInferiors.remove(var0);
            allBaseAfterSleepInBedAtSuperiors.remove(var0);
            allBaseAfterSleepInBedAtInferiors.remove(var0);
            beforeSleepInBedAtHookTypes.remove(var0);
            overrideSleepInBedAtHookTypes.remove(var0);
            afterSleepInBedAtHookTypes.remove(var0);
            allBaseBeforeSwingItemSuperiors.remove(var0);
            allBaseBeforeSwingItemInferiors.remove(var0);
            allBaseOverrideSwingItemSuperiors.remove(var0);
            allBaseOverrideSwingItemInferiors.remove(var0);
            allBaseAfterSwingItemSuperiors.remove(var0);
            allBaseAfterSwingItemInferiors.remove(var0);
            beforeSwingItemHookTypes.remove(var0);
            overrideSwingItemHookTypes.remove(var0);
            afterSwingItemHookTypes.remove(var0);
            allBaseBeforeUpdateCloakSuperiors.remove(var0);
            allBaseBeforeUpdateCloakInferiors.remove(var0);
            allBaseOverrideUpdateCloakSuperiors.remove(var0);
            allBaseOverrideUpdateCloakInferiors.remove(var0);
            allBaseAfterUpdateCloakSuperiors.remove(var0);
            allBaseAfterUpdateCloakInferiors.remove(var0);
            beforeUpdateCloakHookTypes.remove(var0);
            overrideUpdateCloakHookTypes.remove(var0);
            afterUpdateCloakHookTypes.remove(var0);
            allBaseBeforeUpdateEntityActionStateSuperiors.remove(var0);
            allBaseBeforeUpdateEntityActionStateInferiors.remove(var0);
            allBaseOverrideUpdateEntityActionStateSuperiors.remove(var0);
            allBaseOverrideUpdateEntityActionStateInferiors.remove(var0);
            allBaseAfterUpdateEntityActionStateSuperiors.remove(var0);
            allBaseAfterUpdateEntityActionStateInferiors.remove(var0);
            beforeUpdateEntityActionStateHookTypes.remove(var0);
            overrideUpdateEntityActionStateHookTypes.remove(var0);
            afterUpdateEntityActionStateHookTypes.remove(var0);
            allBaseBeforeUpdateRiddenSuperiors.remove(var0);
            allBaseBeforeUpdateRiddenInferiors.remove(var0);
            allBaseOverrideUpdateRiddenSuperiors.remove(var0);
            allBaseOverrideUpdateRiddenInferiors.remove(var0);
            allBaseAfterUpdateRiddenSuperiors.remove(var0);
            allBaseAfterUpdateRiddenInferiors.remove(var0);
            beforeUpdateRiddenHookTypes.remove(var0);
            overrideUpdateRiddenHookTypes.remove(var0);
            afterUpdateRiddenHookTypes.remove(var0);
            allBaseBeforeWriteEntityToNBTSuperiors.remove(var0);
            allBaseBeforeWriteEntityToNBTInferiors.remove(var0);
            allBaseOverrideWriteEntityToNBTSuperiors.remove(var0);
            allBaseOverrideWriteEntityToNBTInferiors.remove(var0);
            allBaseAfterWriteEntityToNBTSuperiors.remove(var0);
            allBaseAfterWriteEntityToNBTInferiors.remove(var0);
            beforeWriteEntityToNBTHookTypes.remove(var0);
            overrideWriteEntityToNBTHookTypes.remove(var0);
            afterWriteEntityToNBTHookTypes.remove(var0);
            log("PlayerAPI: unregistered id \'" + var0 + "\'");
            return true;
        }
        else
        {
            return false;
        }
    }

    public static Set getRegisteredIds()
    {
        return unmodifiableAllIds;
    }

    private static void addSorting(String var0, Map var1, String[] var2)
    {
        if (var2 != null && var2.length > 0)
        {
            var1.put(var0, var2);
        }
    }

    private static boolean addMethod(String var0, Class var1, List var2, String var3, Class ... var4)
    {
        try
        {
            Method var5 = var1.getMethod(var3, var4);
            boolean var6 = var5.getDeclaringClass() != PlayerBase.class;

            if (var6)
            {
                var2.add(var0);
            }

            return var6;
        }
        catch (Exception var7)
        {
            throw new RuntimeException("Can not reflect method \'" + var3 + "\' of class \'" + var1.getName() + "\'", var7);
        }
    }

    public static PlayerAPI create(EntityPlayerSP var0)
    {
        if (allBaseConstructors.size() > 0)
        {
            if (!initialized)
            {
                initialize();
            }

            return new PlayerAPI(var0);
        }
        else
        {
            return null;
        }
    }

    private static void initialize()
    {
        sortBases(beforeLocalConstructingHookTypes, allBaseBeforeLocalConstructingSuperiors, allBaseBeforeLocalConstructingInferiors, "beforeLocalConstructing");
        sortBases(afterLocalConstructingHookTypes, allBaseAfterLocalConstructingSuperiors, allBaseAfterLocalConstructingInferiors, "afterLocalConstructing");
        sortBases(beforeAddExhaustionHookTypes, allBaseBeforeAddExhaustionSuperiors, allBaseBeforeAddExhaustionInferiors, "beforeAddExhaustion");
        sortBases(overrideAddExhaustionHookTypes, allBaseOverrideAddExhaustionSuperiors, allBaseOverrideAddExhaustionInferiors, "overrideAddExhaustion");
        sortBases(afterAddExhaustionHookTypes, allBaseAfterAddExhaustionSuperiors, allBaseAfterAddExhaustionInferiors, "afterAddExhaustion");
        sortBases(beforeAddMovementStatHookTypes, allBaseBeforeAddMovementStatSuperiors, allBaseBeforeAddMovementStatInferiors, "beforeAddMovementStat");
        sortBases(overrideAddMovementStatHookTypes, allBaseOverrideAddMovementStatSuperiors, allBaseOverrideAddMovementStatInferiors, "overrideAddMovementStat");
        sortBases(afterAddMovementStatHookTypes, allBaseAfterAddMovementStatSuperiors, allBaseAfterAddMovementStatInferiors, "afterAddMovementStat");
        sortBases(beforeAddStatHookTypes, allBaseBeforeAddStatSuperiors, allBaseBeforeAddStatInferiors, "beforeAddStat");
        sortBases(overrideAddStatHookTypes, allBaseOverrideAddStatSuperiors, allBaseOverrideAddStatInferiors, "overrideAddStat");
        sortBases(afterAddStatHookTypes, allBaseAfterAddStatSuperiors, allBaseAfterAddStatInferiors, "afterAddStat");
        sortBases(beforeAttackEntityFromHookTypes, allBaseBeforeAttackEntityFromSuperiors, allBaseBeforeAttackEntityFromInferiors, "beforeAttackEntityFrom");
        sortBases(overrideAttackEntityFromHookTypes, allBaseOverrideAttackEntityFromSuperiors, allBaseOverrideAttackEntityFromInferiors, "overrideAttackEntityFrom");
        sortBases(afterAttackEntityFromHookTypes, allBaseAfterAttackEntityFromSuperiors, allBaseAfterAttackEntityFromInferiors, "afterAttackEntityFrom");
        sortBases(beforeAlertWolvesHookTypes, allBaseBeforeAlertWolvesSuperiors, allBaseBeforeAlertWolvesInferiors, "beforeAlertWolves");
        sortBases(overrideAlertWolvesHookTypes, allBaseOverrideAlertWolvesSuperiors, allBaseOverrideAlertWolvesInferiors, "overrideAlertWolves");
        sortBases(afterAlertWolvesHookTypes, allBaseAfterAlertWolvesSuperiors, allBaseAfterAlertWolvesInferiors, "afterAlertWolves");
        sortBases(beforeAttackTargetEntityWithCurrentItemHookTypes, allBaseBeforeAttackTargetEntityWithCurrentItemSuperiors, allBaseBeforeAttackTargetEntityWithCurrentItemInferiors, "beforeAttackTargetEntityWithCurrentItem");
        sortBases(overrideAttackTargetEntityWithCurrentItemHookTypes, allBaseOverrideAttackTargetEntityWithCurrentItemSuperiors, allBaseOverrideAttackTargetEntityWithCurrentItemInferiors, "overrideAttackTargetEntityWithCurrentItem");
        sortBases(afterAttackTargetEntityWithCurrentItemHookTypes, allBaseAfterAttackTargetEntityWithCurrentItemSuperiors, allBaseAfterAttackTargetEntityWithCurrentItemInferiors, "afterAttackTargetEntityWithCurrentItem");
        sortBases(beforeCanBreatheUnderwaterHookTypes, allBaseBeforeCanBreatheUnderwaterSuperiors, allBaseBeforeCanBreatheUnderwaterInferiors, "beforeCanBreatheUnderwater");
        sortBases(overrideCanBreatheUnderwaterHookTypes, allBaseOverrideCanBreatheUnderwaterSuperiors, allBaseOverrideCanBreatheUnderwaterInferiors, "overrideCanBreatheUnderwater");
        sortBases(afterCanBreatheUnderwaterHookTypes, allBaseAfterCanBreatheUnderwaterSuperiors, allBaseAfterCanBreatheUnderwaterInferiors, "afterCanBreatheUnderwater");
        sortBases(beforeCanHarvestBlockHookTypes, allBaseBeforeCanHarvestBlockSuperiors, allBaseBeforeCanHarvestBlockInferiors, "beforeCanHarvestBlock");
        sortBases(overrideCanHarvestBlockHookTypes, allBaseOverrideCanHarvestBlockSuperiors, allBaseOverrideCanHarvestBlockInferiors, "overrideCanHarvestBlock");
        sortBases(afterCanHarvestBlockHookTypes, allBaseAfterCanHarvestBlockSuperiors, allBaseAfterCanHarvestBlockInferiors, "afterCanHarvestBlock");
        sortBases(beforeCanPlayerEditHookTypes, allBaseBeforeCanPlayerEditSuperiors, allBaseBeforeCanPlayerEditInferiors, "beforeCanPlayerEdit");
        sortBases(overrideCanPlayerEditHookTypes, allBaseOverrideCanPlayerEditSuperiors, allBaseOverrideCanPlayerEditInferiors, "overrideCanPlayerEdit");
        sortBases(afterCanPlayerEditHookTypes, allBaseAfterCanPlayerEditSuperiors, allBaseAfterCanPlayerEditInferiors, "afterCanPlayerEdit");
        sortBases(beforeCanTriggerWalkingHookTypes, allBaseBeforeCanTriggerWalkingSuperiors, allBaseBeforeCanTriggerWalkingInferiors, "beforeCanTriggerWalking");
        sortBases(overrideCanTriggerWalkingHookTypes, allBaseOverrideCanTriggerWalkingSuperiors, allBaseOverrideCanTriggerWalkingInferiors, "overrideCanTriggerWalking");
        sortBases(afterCanTriggerWalkingHookTypes, allBaseAfterCanTriggerWalkingSuperiors, allBaseAfterCanTriggerWalkingInferiors, "afterCanTriggerWalking");
        sortBases(beforeCloseScreenHookTypes, allBaseBeforeCloseScreenSuperiors, allBaseBeforeCloseScreenInferiors, "beforeCloseScreen");
        sortBases(overrideCloseScreenHookTypes, allBaseOverrideCloseScreenSuperiors, allBaseOverrideCloseScreenInferiors, "overrideCloseScreen");
        sortBases(afterCloseScreenHookTypes, allBaseAfterCloseScreenSuperiors, allBaseAfterCloseScreenInferiors, "afterCloseScreen");
        sortBases(beforeDamageEntityHookTypes, allBaseBeforeDamageEntitySuperiors, allBaseBeforeDamageEntityInferiors, "beforeDamageEntity");
        sortBases(overrideDamageEntityHookTypes, allBaseOverrideDamageEntitySuperiors, allBaseOverrideDamageEntityInferiors, "overrideDamageEntity");
        sortBases(afterDamageEntityHookTypes, allBaseAfterDamageEntitySuperiors, allBaseAfterDamageEntityInferiors, "afterDamageEntity");
        sortBases(beforeDisplayGUIBrewingStandHookTypes, allBaseBeforeDisplayGUIBrewingStandSuperiors, allBaseBeforeDisplayGUIBrewingStandInferiors, "beforeDisplayGUIBrewingStand");
        sortBases(overrideDisplayGUIBrewingStandHookTypes, allBaseOverrideDisplayGUIBrewingStandSuperiors, allBaseOverrideDisplayGUIBrewingStandInferiors, "overrideDisplayGUIBrewingStand");
        sortBases(afterDisplayGUIBrewingStandHookTypes, allBaseAfterDisplayGUIBrewingStandSuperiors, allBaseAfterDisplayGUIBrewingStandInferiors, "afterDisplayGUIBrewingStand");
        sortBases(beforeDisplayGUIChestHookTypes, allBaseBeforeDisplayGUIChestSuperiors, allBaseBeforeDisplayGUIChestInferiors, "beforeDisplayGUIChest");
        sortBases(overrideDisplayGUIChestHookTypes, allBaseOverrideDisplayGUIChestSuperiors, allBaseOverrideDisplayGUIChestInferiors, "overrideDisplayGUIChest");
        sortBases(afterDisplayGUIChestHookTypes, allBaseAfterDisplayGUIChestSuperiors, allBaseAfterDisplayGUIChestInferiors, "afterDisplayGUIChest");
        sortBases(beforeDisplayGUIDispenserHookTypes, allBaseBeforeDisplayGUIDispenserSuperiors, allBaseBeforeDisplayGUIDispenserInferiors, "beforeDisplayGUIDispenser");
        sortBases(overrideDisplayGUIDispenserHookTypes, allBaseOverrideDisplayGUIDispenserSuperiors, allBaseOverrideDisplayGUIDispenserInferiors, "overrideDisplayGUIDispenser");
        sortBases(afterDisplayGUIDispenserHookTypes, allBaseAfterDisplayGUIDispenserSuperiors, allBaseAfterDisplayGUIDispenserInferiors, "afterDisplayGUIDispenser");
        sortBases(beforeDisplayGUIEditSignHookTypes, allBaseBeforeDisplayGUIEditSignSuperiors, allBaseBeforeDisplayGUIEditSignInferiors, "beforeDisplayGUIEditSign");
        sortBases(overrideDisplayGUIEditSignHookTypes, allBaseOverrideDisplayGUIEditSignSuperiors, allBaseOverrideDisplayGUIEditSignInferiors, "overrideDisplayGUIEditSign");
        sortBases(afterDisplayGUIEditSignHookTypes, allBaseAfterDisplayGUIEditSignSuperiors, allBaseAfterDisplayGUIEditSignInferiors, "afterDisplayGUIEditSign");
        sortBases(beforeDisplayGUIEnchantmentHookTypes, allBaseBeforeDisplayGUIEnchantmentSuperiors, allBaseBeforeDisplayGUIEnchantmentInferiors, "beforeDisplayGUIEnchantment");
        sortBases(overrideDisplayGUIEnchantmentHookTypes, allBaseOverrideDisplayGUIEnchantmentSuperiors, allBaseOverrideDisplayGUIEnchantmentInferiors, "overrideDisplayGUIEnchantment");
        sortBases(afterDisplayGUIEnchantmentHookTypes, allBaseAfterDisplayGUIEnchantmentSuperiors, allBaseAfterDisplayGUIEnchantmentInferiors, "afterDisplayGUIEnchantment");
        sortBases(beforeDisplayGUIFurnaceHookTypes, allBaseBeforeDisplayGUIFurnaceSuperiors, allBaseBeforeDisplayGUIFurnaceInferiors, "beforeDisplayGUIFurnace");
        sortBases(overrideDisplayGUIFurnaceHookTypes, allBaseOverrideDisplayGUIFurnaceSuperiors, allBaseOverrideDisplayGUIFurnaceInferiors, "overrideDisplayGUIFurnace");
        sortBases(afterDisplayGUIFurnaceHookTypes, allBaseAfterDisplayGUIFurnaceSuperiors, allBaseAfterDisplayGUIFurnaceInferiors, "afterDisplayGUIFurnace");
        sortBases(beforeDisplayGUIWorkbenchHookTypes, allBaseBeforeDisplayGUIWorkbenchSuperiors, allBaseBeforeDisplayGUIWorkbenchInferiors, "beforeDisplayGUIWorkbench");
        sortBases(overrideDisplayGUIWorkbenchHookTypes, allBaseOverrideDisplayGUIWorkbenchSuperiors, allBaseOverrideDisplayGUIWorkbenchInferiors, "overrideDisplayGUIWorkbench");
        sortBases(afterDisplayGUIWorkbenchHookTypes, allBaseAfterDisplayGUIWorkbenchSuperiors, allBaseAfterDisplayGUIWorkbenchInferiors, "afterDisplayGUIWorkbench");
        sortBases(beforeDropOneItemHookTypes, allBaseBeforeDropOneItemSuperiors, allBaseBeforeDropOneItemInferiors, "beforeDropOneItem");
        sortBases(overrideDropOneItemHookTypes, allBaseOverrideDropOneItemSuperiors, allBaseOverrideDropOneItemInferiors, "overrideDropOneItem");
        sortBases(afterDropOneItemHookTypes, allBaseAfterDropOneItemSuperiors, allBaseAfterDropOneItemInferiors, "afterDropOneItem");
        sortBases(beforeDropPlayerItemHookTypes, allBaseBeforeDropPlayerItemSuperiors, allBaseBeforeDropPlayerItemInferiors, "beforeDropPlayerItem");
        sortBases(overrideDropPlayerItemHookTypes, allBaseOverrideDropPlayerItemSuperiors, allBaseOverrideDropPlayerItemInferiors, "overrideDropPlayerItem");
        sortBases(afterDropPlayerItemHookTypes, allBaseAfterDropPlayerItemSuperiors, allBaseAfterDropPlayerItemInferiors, "afterDropPlayerItem");
        sortBases(beforeDropPlayerItemWithRandomChoiceHookTypes, allBaseBeforeDropPlayerItemWithRandomChoiceSuperiors, allBaseBeforeDropPlayerItemWithRandomChoiceInferiors, "beforeDropPlayerItemWithRandomChoice");
        sortBases(overrideDropPlayerItemWithRandomChoiceHookTypes, allBaseOverrideDropPlayerItemWithRandomChoiceSuperiors, allBaseOverrideDropPlayerItemWithRandomChoiceInferiors, "overrideDropPlayerItemWithRandomChoice");
        sortBases(afterDropPlayerItemWithRandomChoiceHookTypes, allBaseAfterDropPlayerItemWithRandomChoiceSuperiors, allBaseAfterDropPlayerItemWithRandomChoiceInferiors, "afterDropPlayerItemWithRandomChoice");
        sortBases(beforeFallHookTypes, allBaseBeforeFallSuperiors, allBaseBeforeFallInferiors, "beforeFall");
        sortBases(overrideFallHookTypes, allBaseOverrideFallSuperiors, allBaseOverrideFallInferiors, "overrideFall");
        sortBases(afterFallHookTypes, allBaseAfterFallSuperiors, allBaseAfterFallInferiors, "afterFall");
        sortBases(beforeGetBrightnessHookTypes, allBaseBeforeGetBrightnessSuperiors, allBaseBeforeGetBrightnessInferiors, "beforeGetBrightness");
        sortBases(overrideGetBrightnessHookTypes, allBaseOverrideGetBrightnessSuperiors, allBaseOverrideGetBrightnessInferiors, "overrideGetBrightness");
        sortBases(afterGetBrightnessHookTypes, allBaseAfterGetBrightnessSuperiors, allBaseAfterGetBrightnessInferiors, "afterGetBrightness");
        sortBases(beforeGetBrightnessForRenderHookTypes, allBaseBeforeGetBrightnessForRenderSuperiors, allBaseBeforeGetBrightnessForRenderInferiors, "beforeGetBrightnessForRender");
        sortBases(overrideGetBrightnessForRenderHookTypes, allBaseOverrideGetBrightnessForRenderSuperiors, allBaseOverrideGetBrightnessForRenderInferiors, "overrideGetBrightnessForRender");
        sortBases(afterGetBrightnessForRenderHookTypes, allBaseAfterGetBrightnessForRenderSuperiors, allBaseAfterGetBrightnessForRenderInferiors, "afterGetBrightnessForRender");
        sortBases(beforeGetCurrentPlayerStrVsBlockHookTypes, allBaseBeforeGetCurrentPlayerStrVsBlockSuperiors, allBaseBeforeGetCurrentPlayerStrVsBlockInferiors, "beforeGetCurrentPlayerStrVsBlock");
        sortBases(overrideGetCurrentPlayerStrVsBlockHookTypes, allBaseOverrideGetCurrentPlayerStrVsBlockSuperiors, allBaseOverrideGetCurrentPlayerStrVsBlockInferiors, "overrideGetCurrentPlayerStrVsBlock");
        sortBases(afterGetCurrentPlayerStrVsBlockHookTypes, allBaseAfterGetCurrentPlayerStrVsBlockSuperiors, allBaseAfterGetCurrentPlayerStrVsBlockInferiors, "afterGetCurrentPlayerStrVsBlock");
        sortBases(beforeGetDistanceSqHookTypes, allBaseBeforeGetDistanceSqSuperiors, allBaseBeforeGetDistanceSqInferiors, "beforeGetDistanceSq");
        sortBases(overrideGetDistanceSqHookTypes, allBaseOverrideGetDistanceSqSuperiors, allBaseOverrideGetDistanceSqInferiors, "overrideGetDistanceSq");
        sortBases(afterGetDistanceSqHookTypes, allBaseAfterGetDistanceSqSuperiors, allBaseAfterGetDistanceSqInferiors, "afterGetDistanceSq");
        sortBases(beforeGetDistanceSqToEntityHookTypes, allBaseBeforeGetDistanceSqToEntitySuperiors, allBaseBeforeGetDistanceSqToEntityInferiors, "beforeGetDistanceSqToEntity");
        sortBases(overrideGetDistanceSqToEntityHookTypes, allBaseOverrideGetDistanceSqToEntitySuperiors, allBaseOverrideGetDistanceSqToEntityInferiors, "overrideGetDistanceSqToEntity");
        sortBases(afterGetDistanceSqToEntityHookTypes, allBaseAfterGetDistanceSqToEntitySuperiors, allBaseAfterGetDistanceSqToEntityInferiors, "afterGetDistanceSqToEntity");
        sortBases(beforeGetFOVMultiplierHookTypes, allBaseBeforeGetFOVMultiplierSuperiors, allBaseBeforeGetFOVMultiplierInferiors, "beforeGetFOVMultiplier");
        sortBases(overrideGetFOVMultiplierHookTypes, allBaseOverrideGetFOVMultiplierSuperiors, allBaseOverrideGetFOVMultiplierInferiors, "overrideGetFOVMultiplier");
        sortBases(afterGetFOVMultiplierHookTypes, allBaseAfterGetFOVMultiplierSuperiors, allBaseAfterGetFOVMultiplierInferiors, "afterGetFOVMultiplier");
        sortBases(beforeGetHurtSoundHookTypes, allBaseBeforeGetHurtSoundSuperiors, allBaseBeforeGetHurtSoundInferiors, "beforeGetHurtSound");
        sortBases(overrideGetHurtSoundHookTypes, allBaseOverrideGetHurtSoundSuperiors, allBaseOverrideGetHurtSoundInferiors, "overrideGetHurtSound");
        sortBases(afterGetHurtSoundHookTypes, allBaseAfterGetHurtSoundSuperiors, allBaseAfterGetHurtSoundInferiors, "afterGetHurtSound");
        sortBases(beforeGetItemIconHookTypes, allBaseBeforeGetItemIconSuperiors, allBaseBeforeGetItemIconInferiors, "beforeGetItemIcon");
        sortBases(overrideGetItemIconHookTypes, allBaseOverrideGetItemIconSuperiors, allBaseOverrideGetItemIconInferiors, "overrideGetItemIcon");
        sortBases(afterGetItemIconHookTypes, allBaseAfterGetItemIconSuperiors, allBaseAfterGetItemIconInferiors, "afterGetItemIcon");
        sortBases(beforeGetMaxHealthHookTypes, allBaseBeforeGetMaxHealthSuperiors, allBaseBeforeGetMaxHealthInferiors, "beforeGetMaxHealth");
        sortBases(overrideGetMaxHealthHookTypes, allBaseOverrideGetMaxHealthSuperiors, allBaseOverrideGetMaxHealthInferiors, "overrideGetMaxHealth");
        sortBases(afterGetMaxHealthHookTypes, allBaseAfterGetMaxHealthSuperiors, allBaseAfterGetMaxHealthInferiors, "afterGetMaxHealth");
        sortBases(beforeGetSleepTimerHookTypes, allBaseBeforeGetSleepTimerSuperiors, allBaseBeforeGetSleepTimerInferiors, "beforeGetSleepTimer");
        sortBases(overrideGetSleepTimerHookTypes, allBaseOverrideGetSleepTimerSuperiors, allBaseOverrideGetSleepTimerInferiors, "overrideGetSleepTimer");
        sortBases(afterGetSleepTimerHookTypes, allBaseAfterGetSleepTimerSuperiors, allBaseAfterGetSleepTimerInferiors, "afterGetSleepTimer");
        sortBases(beforeGetSpeedModifierHookTypes, allBaseBeforeGetSpeedModifierSuperiors, allBaseBeforeGetSpeedModifierInferiors, "beforeGetSpeedModifier");
        sortBases(overrideGetSpeedModifierHookTypes, allBaseOverrideGetSpeedModifierSuperiors, allBaseOverrideGetSpeedModifierInferiors, "overrideGetSpeedModifier");
        sortBases(afterGetSpeedModifierHookTypes, allBaseAfterGetSpeedModifierSuperiors, allBaseAfterGetSpeedModifierInferiors, "afterGetSpeedModifier");
        sortBases(beforeHandleLavaMovementHookTypes, allBaseBeforeHandleLavaMovementSuperiors, allBaseBeforeHandleLavaMovementInferiors, "beforeHandleLavaMovement");
        sortBases(overrideHandleLavaMovementHookTypes, allBaseOverrideHandleLavaMovementSuperiors, allBaseOverrideHandleLavaMovementInferiors, "overrideHandleLavaMovement");
        sortBases(afterHandleLavaMovementHookTypes, allBaseAfterHandleLavaMovementSuperiors, allBaseAfterHandleLavaMovementInferiors, "afterHandleLavaMovement");
        sortBases(beforeHandleWaterMovementHookTypes, allBaseBeforeHandleWaterMovementSuperiors, allBaseBeforeHandleWaterMovementInferiors, "beforeHandleWaterMovement");
        sortBases(overrideHandleWaterMovementHookTypes, allBaseOverrideHandleWaterMovementSuperiors, allBaseOverrideHandleWaterMovementInferiors, "overrideHandleWaterMovement");
        sortBases(afterHandleWaterMovementHookTypes, allBaseAfterHandleWaterMovementSuperiors, allBaseAfterHandleWaterMovementInferiors, "afterHandleWaterMovement");
        sortBases(beforeHealHookTypes, allBaseBeforeHealSuperiors, allBaseBeforeHealInferiors, "beforeHeal");
        sortBases(overrideHealHookTypes, allBaseOverrideHealSuperiors, allBaseOverrideHealInferiors, "overrideHeal");
        sortBases(afterHealHookTypes, allBaseAfterHealSuperiors, allBaseAfterHealInferiors, "afterHeal");
        sortBases(beforeInteractHookTypes, allBaseBeforeInteractSuperiors, allBaseBeforeInteractInferiors, "beforeInteract");
        sortBases(overrideInteractHookTypes, allBaseOverrideInteractSuperiors, allBaseOverrideInteractInferiors, "overrideInteract");
        sortBases(afterInteractHookTypes, allBaseAfterInteractSuperiors, allBaseAfterInteractInferiors, "afterInteract");
        sortBases(beforeIsEntityInsideOpaqueBlockHookTypes, allBaseBeforeIsEntityInsideOpaqueBlockSuperiors, allBaseBeforeIsEntityInsideOpaqueBlockInferiors, "beforeIsEntityInsideOpaqueBlock");
        sortBases(overrideIsEntityInsideOpaqueBlockHookTypes, allBaseOverrideIsEntityInsideOpaqueBlockSuperiors, allBaseOverrideIsEntityInsideOpaqueBlockInferiors, "overrideIsEntityInsideOpaqueBlock");
        sortBases(afterIsEntityInsideOpaqueBlockHookTypes, allBaseAfterIsEntityInsideOpaqueBlockSuperiors, allBaseAfterIsEntityInsideOpaqueBlockInferiors, "afterIsEntityInsideOpaqueBlock");
        sortBases(beforeIsInWaterHookTypes, allBaseBeforeIsInWaterSuperiors, allBaseBeforeIsInWaterInferiors, "beforeIsInWater");
        sortBases(overrideIsInWaterHookTypes, allBaseOverrideIsInWaterSuperiors, allBaseOverrideIsInWaterInferiors, "overrideIsInWater");
        sortBases(afterIsInWaterHookTypes, allBaseAfterIsInWaterSuperiors, allBaseAfterIsInWaterInferiors, "afterIsInWater");
        sortBases(beforeIsInsideOfMaterialHookTypes, allBaseBeforeIsInsideOfMaterialSuperiors, allBaseBeforeIsInsideOfMaterialInferiors, "beforeIsInsideOfMaterial");
        sortBases(overrideIsInsideOfMaterialHookTypes, allBaseOverrideIsInsideOfMaterialSuperiors, allBaseOverrideIsInsideOfMaterialInferiors, "overrideIsInsideOfMaterial");
        sortBases(afterIsInsideOfMaterialHookTypes, allBaseAfterIsInsideOfMaterialSuperiors, allBaseAfterIsInsideOfMaterialInferiors, "afterIsInsideOfMaterial");
        sortBases(beforeIsOnLadderHookTypes, allBaseBeforeIsOnLadderSuperiors, allBaseBeforeIsOnLadderInferiors, "beforeIsOnLadder");
        sortBases(overrideIsOnLadderHookTypes, allBaseOverrideIsOnLadderSuperiors, allBaseOverrideIsOnLadderInferiors, "overrideIsOnLadder");
        sortBases(afterIsOnLadderHookTypes, allBaseAfterIsOnLadderSuperiors, allBaseAfterIsOnLadderInferiors, "afterIsOnLadder");
        sortBases(beforeIsPlayerSleepingHookTypes, allBaseBeforeIsPlayerSleepingSuperiors, allBaseBeforeIsPlayerSleepingInferiors, "beforeIsPlayerSleeping");
        sortBases(overrideIsPlayerSleepingHookTypes, allBaseOverrideIsPlayerSleepingSuperiors, allBaseOverrideIsPlayerSleepingInferiors, "overrideIsPlayerSleeping");
        sortBases(afterIsPlayerSleepingHookTypes, allBaseAfterIsPlayerSleepingSuperiors, allBaseAfterIsPlayerSleepingInferiors, "afterIsPlayerSleeping");
        sortBases(beforeIsSneakingHookTypes, allBaseBeforeIsSneakingSuperiors, allBaseBeforeIsSneakingInferiors, "beforeIsSneaking");
        sortBases(overrideIsSneakingHookTypes, allBaseOverrideIsSneakingSuperiors, allBaseOverrideIsSneakingInferiors, "overrideIsSneaking");
        sortBases(afterIsSneakingHookTypes, allBaseAfterIsSneakingSuperiors, allBaseAfterIsSneakingInferiors, "afterIsSneaking");
        sortBases(beforeIsSprintingHookTypes, allBaseBeforeIsSprintingSuperiors, allBaseBeforeIsSprintingInferiors, "beforeIsSprinting");
        sortBases(overrideIsSprintingHookTypes, allBaseOverrideIsSprintingSuperiors, allBaseOverrideIsSprintingInferiors, "overrideIsSprinting");
        sortBases(afterIsSprintingHookTypes, allBaseAfterIsSprintingSuperiors, allBaseAfterIsSprintingInferiors, "afterIsSprinting");
        sortBases(beforeJumpHookTypes, allBaseBeforeJumpSuperiors, allBaseBeforeJumpInferiors, "beforeJump");
        sortBases(overrideJumpHookTypes, allBaseOverrideJumpSuperiors, allBaseOverrideJumpInferiors, "overrideJump");
        sortBases(afterJumpHookTypes, allBaseAfterJumpSuperiors, allBaseAfterJumpInferiors, "afterJump");
        sortBases(beforeKnockBackHookTypes, allBaseBeforeKnockBackSuperiors, allBaseBeforeKnockBackInferiors, "beforeKnockBack");
        sortBases(overrideKnockBackHookTypes, allBaseOverrideKnockBackSuperiors, allBaseOverrideKnockBackInferiors, "overrideKnockBack");
        sortBases(afterKnockBackHookTypes, allBaseAfterKnockBackSuperiors, allBaseAfterKnockBackInferiors, "afterKnockBack");
        sortBases(beforeMoveEntityHookTypes, allBaseBeforeMoveEntitySuperiors, allBaseBeforeMoveEntityInferiors, "beforeMoveEntity");
        sortBases(overrideMoveEntityHookTypes, allBaseOverrideMoveEntitySuperiors, allBaseOverrideMoveEntityInferiors, "overrideMoveEntity");
        sortBases(afterMoveEntityHookTypes, allBaseAfterMoveEntitySuperiors, allBaseAfterMoveEntityInferiors, "afterMoveEntity");
        sortBases(beforeMoveEntityWithHeadingHookTypes, allBaseBeforeMoveEntityWithHeadingSuperiors, allBaseBeforeMoveEntityWithHeadingInferiors, "beforeMoveEntityWithHeading");
        sortBases(overrideMoveEntityWithHeadingHookTypes, allBaseOverrideMoveEntityWithHeadingSuperiors, allBaseOverrideMoveEntityWithHeadingInferiors, "overrideMoveEntityWithHeading");
        sortBases(afterMoveEntityWithHeadingHookTypes, allBaseAfterMoveEntityWithHeadingSuperiors, allBaseAfterMoveEntityWithHeadingInferiors, "afterMoveEntityWithHeading");
        sortBases(beforeMoveFlyingHookTypes, allBaseBeforeMoveFlyingSuperiors, allBaseBeforeMoveFlyingInferiors, "beforeMoveFlying");
        sortBases(overrideMoveFlyingHookTypes, allBaseOverrideMoveFlyingSuperiors, allBaseOverrideMoveFlyingInferiors, "overrideMoveFlying");
        sortBases(afterMoveFlyingHookTypes, allBaseAfterMoveFlyingSuperiors, allBaseAfterMoveFlyingInferiors, "afterMoveFlying");
        sortBases(beforeOnDeathHookTypes, allBaseBeforeOnDeathSuperiors, allBaseBeforeOnDeathInferiors, "beforeOnDeath");
        sortBases(overrideOnDeathHookTypes, allBaseOverrideOnDeathSuperiors, allBaseOverrideOnDeathInferiors, "overrideOnDeath");
        sortBases(afterOnDeathHookTypes, allBaseAfterOnDeathSuperiors, allBaseAfterOnDeathInferiors, "afterOnDeath");
        sortBases(beforeOnLivingUpdateHookTypes, allBaseBeforeOnLivingUpdateSuperiors, allBaseBeforeOnLivingUpdateInferiors, "beforeOnLivingUpdate");
        sortBases(overrideOnLivingUpdateHookTypes, allBaseOverrideOnLivingUpdateSuperiors, allBaseOverrideOnLivingUpdateInferiors, "overrideOnLivingUpdate");
        sortBases(afterOnLivingUpdateHookTypes, allBaseAfterOnLivingUpdateSuperiors, allBaseAfterOnLivingUpdateInferiors, "afterOnLivingUpdate");
        sortBases(beforeOnKillEntityHookTypes, allBaseBeforeOnKillEntitySuperiors, allBaseBeforeOnKillEntityInferiors, "beforeOnKillEntity");
        sortBases(overrideOnKillEntityHookTypes, allBaseOverrideOnKillEntitySuperiors, allBaseOverrideOnKillEntityInferiors, "overrideOnKillEntity");
        sortBases(afterOnKillEntityHookTypes, allBaseAfterOnKillEntitySuperiors, allBaseAfterOnKillEntityInferiors, "afterOnKillEntity");
        sortBases(beforeOnStruckByLightningHookTypes, allBaseBeforeOnStruckByLightningSuperiors, allBaseBeforeOnStruckByLightningInferiors, "beforeOnStruckByLightning");
        sortBases(overrideOnStruckByLightningHookTypes, allBaseOverrideOnStruckByLightningSuperiors, allBaseOverrideOnStruckByLightningInferiors, "overrideOnStruckByLightning");
        sortBases(afterOnStruckByLightningHookTypes, allBaseAfterOnStruckByLightningSuperiors, allBaseAfterOnStruckByLightningInferiors, "afterOnStruckByLightning");
        sortBases(beforeOnUpdateHookTypes, allBaseBeforeOnUpdateSuperiors, allBaseBeforeOnUpdateInferiors, "beforeOnUpdate");
        sortBases(overrideOnUpdateHookTypes, allBaseOverrideOnUpdateSuperiors, allBaseOverrideOnUpdateInferiors, "overrideOnUpdate");
        sortBases(afterOnUpdateHookTypes, allBaseAfterOnUpdateSuperiors, allBaseAfterOnUpdateInferiors, "afterOnUpdate");
        sortBases(beforePlayStepSoundHookTypes, allBaseBeforePlayStepSoundSuperiors, allBaseBeforePlayStepSoundInferiors, "beforePlayStepSound");
        sortBases(overridePlayStepSoundHookTypes, allBaseOverridePlayStepSoundSuperiors, allBaseOverridePlayStepSoundInferiors, "overridePlayStepSound");
        sortBases(afterPlayStepSoundHookTypes, allBaseAfterPlayStepSoundSuperiors, allBaseAfterPlayStepSoundInferiors, "afterPlayStepSound");
        sortBases(beforePushOutOfBlocksHookTypes, allBaseBeforePushOutOfBlocksSuperiors, allBaseBeforePushOutOfBlocksInferiors, "beforePushOutOfBlocks");
        sortBases(overridePushOutOfBlocksHookTypes, allBaseOverridePushOutOfBlocksSuperiors, allBaseOverridePushOutOfBlocksInferiors, "overridePushOutOfBlocks");
        sortBases(afterPushOutOfBlocksHookTypes, allBaseAfterPushOutOfBlocksSuperiors, allBaseAfterPushOutOfBlocksInferiors, "afterPushOutOfBlocks");
        sortBases(beforeRayTraceHookTypes, allBaseBeforeRayTraceSuperiors, allBaseBeforeRayTraceInferiors, "beforeRayTrace");
        sortBases(overrideRayTraceHookTypes, allBaseOverrideRayTraceSuperiors, allBaseOverrideRayTraceInferiors, "overrideRayTrace");
        sortBases(afterRayTraceHookTypes, allBaseAfterRayTraceSuperiors, allBaseAfterRayTraceInferiors, "afterRayTrace");
        sortBases(beforeReadEntityFromNBTHookTypes, allBaseBeforeReadEntityFromNBTSuperiors, allBaseBeforeReadEntityFromNBTInferiors, "beforeReadEntityFromNBT");
        sortBases(overrideReadEntityFromNBTHookTypes, allBaseOverrideReadEntityFromNBTSuperiors, allBaseOverrideReadEntityFromNBTInferiors, "overrideReadEntityFromNBT");
        sortBases(afterReadEntityFromNBTHookTypes, allBaseAfterReadEntityFromNBTSuperiors, allBaseAfterReadEntityFromNBTInferiors, "afterReadEntityFromNBT");
        sortBases(beforeRespawnPlayerHookTypes, allBaseBeforeRespawnPlayerSuperiors, allBaseBeforeRespawnPlayerInferiors, "beforeRespawnPlayer");
        sortBases(overrideRespawnPlayerHookTypes, allBaseOverrideRespawnPlayerSuperiors, allBaseOverrideRespawnPlayerInferiors, "overrideRespawnPlayer");
        sortBases(afterRespawnPlayerHookTypes, allBaseAfterRespawnPlayerSuperiors, allBaseAfterRespawnPlayerInferiors, "afterRespawnPlayer");
        sortBases(beforeSetDeadHookTypes, allBaseBeforeSetDeadSuperiors, allBaseBeforeSetDeadInferiors, "beforeSetDead");
        sortBases(overrideSetDeadHookTypes, allBaseOverrideSetDeadSuperiors, allBaseOverrideSetDeadInferiors, "overrideSetDead");
        sortBases(afterSetDeadHookTypes, allBaseAfterSetDeadSuperiors, allBaseAfterSetDeadInferiors, "afterSetDead");
        sortBases(beforeSetPositionAndRotationHookTypes, allBaseBeforeSetPositionAndRotationSuperiors, allBaseBeforeSetPositionAndRotationInferiors, "beforeSetPositionAndRotation");
        sortBases(overrideSetPositionAndRotationHookTypes, allBaseOverrideSetPositionAndRotationSuperiors, allBaseOverrideSetPositionAndRotationInferiors, "overrideSetPositionAndRotation");
        sortBases(afterSetPositionAndRotationHookTypes, allBaseAfterSetPositionAndRotationSuperiors, allBaseAfterSetPositionAndRotationInferiors, "afterSetPositionAndRotation");
        sortBases(beforeSleepInBedAtHookTypes, allBaseBeforeSleepInBedAtSuperiors, allBaseBeforeSleepInBedAtInferiors, "beforeSleepInBedAt");
        sortBases(overrideSleepInBedAtHookTypes, allBaseOverrideSleepInBedAtSuperiors, allBaseOverrideSleepInBedAtInferiors, "overrideSleepInBedAt");
        sortBases(afterSleepInBedAtHookTypes, allBaseAfterSleepInBedAtSuperiors, allBaseAfterSleepInBedAtInferiors, "afterSleepInBedAt");
        sortBases(beforeSwingItemHookTypes, allBaseBeforeSwingItemSuperiors, allBaseBeforeSwingItemInferiors, "beforeSwingItem");
        sortBases(overrideSwingItemHookTypes, allBaseOverrideSwingItemSuperiors, allBaseOverrideSwingItemInferiors, "overrideSwingItem");
        sortBases(afterSwingItemHookTypes, allBaseAfterSwingItemSuperiors, allBaseAfterSwingItemInferiors, "afterSwingItem");
        sortBases(beforeUpdateCloakHookTypes, allBaseBeforeUpdateCloakSuperiors, allBaseBeforeUpdateCloakInferiors, "beforeUpdateCloak");
        sortBases(overrideUpdateCloakHookTypes, allBaseOverrideUpdateCloakSuperiors, allBaseOverrideUpdateCloakInferiors, "overrideUpdateCloak");
        sortBases(afterUpdateCloakHookTypes, allBaseAfterUpdateCloakSuperiors, allBaseAfterUpdateCloakInferiors, "afterUpdateCloak");
        sortBases(beforeUpdateEntityActionStateHookTypes, allBaseBeforeUpdateEntityActionStateSuperiors, allBaseBeforeUpdateEntityActionStateInferiors, "beforeUpdateEntityActionState");
        sortBases(overrideUpdateEntityActionStateHookTypes, allBaseOverrideUpdateEntityActionStateSuperiors, allBaseOverrideUpdateEntityActionStateInferiors, "overrideUpdateEntityActionState");
        sortBases(afterUpdateEntityActionStateHookTypes, allBaseAfterUpdateEntityActionStateSuperiors, allBaseAfterUpdateEntityActionStateInferiors, "afterUpdateEntityActionState");
        sortBases(beforeUpdateRiddenHookTypes, allBaseBeforeUpdateRiddenSuperiors, allBaseBeforeUpdateRiddenInferiors, "beforeUpdateRidden");
        sortBases(overrideUpdateRiddenHookTypes, allBaseOverrideUpdateRiddenSuperiors, allBaseOverrideUpdateRiddenInferiors, "overrideUpdateRidden");
        sortBases(afterUpdateRiddenHookTypes, allBaseAfterUpdateRiddenSuperiors, allBaseAfterUpdateRiddenInferiors, "afterUpdateRidden");
        sortBases(beforeWriteEntityToNBTHookTypes, allBaseBeforeWriteEntityToNBTSuperiors, allBaseBeforeWriteEntityToNBTInferiors, "beforeWriteEntityToNBT");
        sortBases(overrideWriteEntityToNBTHookTypes, allBaseOverrideWriteEntityToNBTSuperiors, allBaseOverrideWriteEntityToNBTInferiors, "overrideWriteEntityToNBT");
        sortBases(afterWriteEntityToNBTHookTypes, allBaseAfterWriteEntityToNBTSuperiors, allBaseAfterWriteEntityToNBTInferiors, "afterWriteEntityToNBT");
        initialized = true;
    }

    public static void beforeLocalConstructing(EntityPlayerSP var0, Minecraft var1, World var2, Session var3, int var4)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.beforeLocalConstructing(var1, var2, var3, var4);
        }
    }

    public static void afterLocalConstructing(EntityPlayerSP var0, Minecraft var1, World var2, Session var3, int var4)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.afterLocalConstructing(var1, var2, var3, var4);
        }
    }

    private static void sortBases(List var0, Map var1, Map var2, String var3)
    {
        (new PlayerBaseSorter(var0, var1, var2, var3)).Sort();
    }

    private PlayerAPI(EntityPlayerSP var1)
    {
        this.unmodifiableAllBaseIds = Collections.unmodifiableSet(this.allBaseObjects.keySet());
        this.player = var1;
        Object[] var2 = new Object[] {this};
        Object[] var3 = new Object[] {this, null};
        Entry var5;
        PlayerBase var7;

        for (Iterator var4 = allBaseConstructors.entrySet().iterator(); var4.hasNext(); this.allBaseObjects.put(var5.getKey(), var7))
        {
            var5 = (Entry)var4.next();
            Constructor var6 = (Constructor)var5.getValue();
            var3[1] = var5.getKey();

            try
            {
                if (var6.getParameterTypes().length == 1)
                {
                    var7 = (PlayerBase)var6.newInstance(var2);
                }
                else
                {
                    var7 = (PlayerBase)var6.newInstance(var3);
                }
            }
            catch (Exception var9)
            {
                throw new RuntimeException("Exception while creating a PlayerBase of type \'" + var6.getDeclaringClass() + "\'", var9);
            }
        }

        this.beforeLocalConstructingHooks = this.create(beforeLocalConstructingHookTypes);
        this.afterLocalConstructingHooks = this.create(afterLocalConstructingHookTypes);
        this.beforeAddExhaustionHooks = this.create(beforeAddExhaustionHookTypes);
        this.overrideAddExhaustionHooks = this.create(overrideAddExhaustionHookTypes);
        this.afterAddExhaustionHooks = this.create(afterAddExhaustionHookTypes);
        this.isAddExhaustionModded = this.beforeAddExhaustionHooks != null || this.overrideAddExhaustionHooks != null || this.afterAddExhaustionHooks != null;
        this.beforeAddMovementStatHooks = this.create(beforeAddMovementStatHookTypes);
        this.overrideAddMovementStatHooks = this.create(overrideAddMovementStatHookTypes);
        this.afterAddMovementStatHooks = this.create(afterAddMovementStatHookTypes);
        this.isAddMovementStatModded = this.beforeAddMovementStatHooks != null || this.overrideAddMovementStatHooks != null || this.afterAddMovementStatHooks != null;
        this.beforeAddStatHooks = this.create(beforeAddStatHookTypes);
        this.overrideAddStatHooks = this.create(overrideAddStatHookTypes);
        this.afterAddStatHooks = this.create(afterAddStatHookTypes);
        this.isAddStatModded = this.beforeAddStatHooks != null || this.overrideAddStatHooks != null || this.afterAddStatHooks != null;
        this.beforeAttackEntityFromHooks = this.create(beforeAttackEntityFromHookTypes);
        this.overrideAttackEntityFromHooks = this.create(overrideAttackEntityFromHookTypes);
        this.afterAttackEntityFromHooks = this.create(afterAttackEntityFromHookTypes);
        this.isAttackEntityFromModded = this.beforeAttackEntityFromHooks != null || this.overrideAttackEntityFromHooks != null || this.afterAttackEntityFromHooks != null;
        this.beforeAlertWolvesHooks = this.create(beforeAlertWolvesHookTypes);
        this.overrideAlertWolvesHooks = this.create(overrideAlertWolvesHookTypes);
        this.afterAlertWolvesHooks = this.create(afterAlertWolvesHookTypes);
        this.isAlertWolvesModded = this.beforeAlertWolvesHooks != null || this.overrideAlertWolvesHooks != null || this.afterAlertWolvesHooks != null;
        this.beforeAttackTargetEntityWithCurrentItemHooks = this.create(beforeAttackTargetEntityWithCurrentItemHookTypes);
        this.overrideAttackTargetEntityWithCurrentItemHooks = this.create(overrideAttackTargetEntityWithCurrentItemHookTypes);
        this.afterAttackTargetEntityWithCurrentItemHooks = this.create(afterAttackTargetEntityWithCurrentItemHookTypes);
        this.isAttackTargetEntityWithCurrentItemModded = this.beforeAttackTargetEntityWithCurrentItemHooks != null || this.overrideAttackTargetEntityWithCurrentItemHooks != null || this.afterAttackTargetEntityWithCurrentItemHooks != null;
        this.beforeCanBreatheUnderwaterHooks = this.create(beforeCanBreatheUnderwaterHookTypes);
        this.overrideCanBreatheUnderwaterHooks = this.create(overrideCanBreatheUnderwaterHookTypes);
        this.afterCanBreatheUnderwaterHooks = this.create(afterCanBreatheUnderwaterHookTypes);
        this.isCanBreatheUnderwaterModded = this.beforeCanBreatheUnderwaterHooks != null || this.overrideCanBreatheUnderwaterHooks != null || this.afterCanBreatheUnderwaterHooks != null;
        this.beforeCanHarvestBlockHooks = this.create(beforeCanHarvestBlockHookTypes);
        this.overrideCanHarvestBlockHooks = this.create(overrideCanHarvestBlockHookTypes);
        this.afterCanHarvestBlockHooks = this.create(afterCanHarvestBlockHookTypes);
        this.isCanHarvestBlockModded = this.beforeCanHarvestBlockHooks != null || this.overrideCanHarvestBlockHooks != null || this.afterCanHarvestBlockHooks != null;
        this.beforeCanPlayerEditHooks = this.create(beforeCanPlayerEditHookTypes);
        this.overrideCanPlayerEditHooks = this.create(overrideCanPlayerEditHookTypes);
        this.afterCanPlayerEditHooks = this.create(afterCanPlayerEditHookTypes);
        this.isCanPlayerEditModded = this.beforeCanPlayerEditHooks != null || this.overrideCanPlayerEditHooks != null || this.afterCanPlayerEditHooks != null;
        this.beforeCanTriggerWalkingHooks = this.create(beforeCanTriggerWalkingHookTypes);
        this.overrideCanTriggerWalkingHooks = this.create(overrideCanTriggerWalkingHookTypes);
        this.afterCanTriggerWalkingHooks = this.create(afterCanTriggerWalkingHookTypes);
        this.isCanTriggerWalkingModded = this.beforeCanTriggerWalkingHooks != null || this.overrideCanTriggerWalkingHooks != null || this.afterCanTriggerWalkingHooks != null;
        this.beforeCloseScreenHooks = this.create(beforeCloseScreenHookTypes);
        this.overrideCloseScreenHooks = this.create(overrideCloseScreenHookTypes);
        this.afterCloseScreenHooks = this.create(afterCloseScreenHookTypes);
        this.isCloseScreenModded = this.beforeCloseScreenHooks != null || this.overrideCloseScreenHooks != null || this.afterCloseScreenHooks != null;
        this.beforeDamageEntityHooks = this.create(beforeDamageEntityHookTypes);
        this.overrideDamageEntityHooks = this.create(overrideDamageEntityHookTypes);
        this.afterDamageEntityHooks = this.create(afterDamageEntityHookTypes);
        this.isDamageEntityModded = this.beforeDamageEntityHooks != null || this.overrideDamageEntityHooks != null || this.afterDamageEntityHooks != null;
        this.beforeDisplayGUIBrewingStandHooks = this.create(beforeDisplayGUIBrewingStandHookTypes);
        this.overrideDisplayGUIBrewingStandHooks = this.create(overrideDisplayGUIBrewingStandHookTypes);
        this.afterDisplayGUIBrewingStandHooks = this.create(afterDisplayGUIBrewingStandHookTypes);
        this.isDisplayGUIBrewingStandModded = this.beforeDisplayGUIBrewingStandHooks != null || this.overrideDisplayGUIBrewingStandHooks != null || this.afterDisplayGUIBrewingStandHooks != null;
        this.beforeDisplayGUIChestHooks = this.create(beforeDisplayGUIChestHookTypes);
        this.overrideDisplayGUIChestHooks = this.create(overrideDisplayGUIChestHookTypes);
        this.afterDisplayGUIChestHooks = this.create(afterDisplayGUIChestHookTypes);
        this.isDisplayGUIChestModded = this.beforeDisplayGUIChestHooks != null || this.overrideDisplayGUIChestHooks != null || this.afterDisplayGUIChestHooks != null;
        this.beforeDisplayGUIDispenserHooks = this.create(beforeDisplayGUIDispenserHookTypes);
        this.overrideDisplayGUIDispenserHooks = this.create(overrideDisplayGUIDispenserHookTypes);
        this.afterDisplayGUIDispenserHooks = this.create(afterDisplayGUIDispenserHookTypes);
        this.isDisplayGUIDispenserModded = this.beforeDisplayGUIDispenserHooks != null || this.overrideDisplayGUIDispenserHooks != null || this.afterDisplayGUIDispenserHooks != null;
        this.beforeDisplayGUIEditSignHooks = this.create(beforeDisplayGUIEditSignHookTypes);
        this.overrideDisplayGUIEditSignHooks = this.create(overrideDisplayGUIEditSignHookTypes);
        this.afterDisplayGUIEditSignHooks = this.create(afterDisplayGUIEditSignHookTypes);
        this.isDisplayGUIEditSignModded = this.beforeDisplayGUIEditSignHooks != null || this.overrideDisplayGUIEditSignHooks != null || this.afterDisplayGUIEditSignHooks != null;
        this.beforeDisplayGUIEnchantmentHooks = this.create(beforeDisplayGUIEnchantmentHookTypes);
        this.overrideDisplayGUIEnchantmentHooks = this.create(overrideDisplayGUIEnchantmentHookTypes);
        this.afterDisplayGUIEnchantmentHooks = this.create(afterDisplayGUIEnchantmentHookTypes);
        this.isDisplayGUIEnchantmentModded = this.beforeDisplayGUIEnchantmentHooks != null || this.overrideDisplayGUIEnchantmentHooks != null || this.afterDisplayGUIEnchantmentHooks != null;
        this.beforeDisplayGUIFurnaceHooks = this.create(beforeDisplayGUIFurnaceHookTypes);
        this.overrideDisplayGUIFurnaceHooks = this.create(overrideDisplayGUIFurnaceHookTypes);
        this.afterDisplayGUIFurnaceHooks = this.create(afterDisplayGUIFurnaceHookTypes);
        this.isDisplayGUIFurnaceModded = this.beforeDisplayGUIFurnaceHooks != null || this.overrideDisplayGUIFurnaceHooks != null || this.afterDisplayGUIFurnaceHooks != null;
        this.beforeDisplayGUIWorkbenchHooks = this.create(beforeDisplayGUIWorkbenchHookTypes);
        this.overrideDisplayGUIWorkbenchHooks = this.create(overrideDisplayGUIWorkbenchHookTypes);
        this.afterDisplayGUIWorkbenchHooks = this.create(afterDisplayGUIWorkbenchHookTypes);
        this.isDisplayGUIWorkbenchModded = this.beforeDisplayGUIWorkbenchHooks != null || this.overrideDisplayGUIWorkbenchHooks != null || this.afterDisplayGUIWorkbenchHooks != null;
        this.beforeDropOneItemHooks = this.create(beforeDropOneItemHookTypes);
        this.overrideDropOneItemHooks = this.create(overrideDropOneItemHookTypes);
        this.afterDropOneItemHooks = this.create(afterDropOneItemHookTypes);
        this.isDropOneItemModded = this.beforeDropOneItemHooks != null || this.overrideDropOneItemHooks != null || this.afterDropOneItemHooks != null;
        this.beforeDropPlayerItemHooks = this.create(beforeDropPlayerItemHookTypes);
        this.overrideDropPlayerItemHooks = this.create(overrideDropPlayerItemHookTypes);
        this.afterDropPlayerItemHooks = this.create(afterDropPlayerItemHookTypes);
        this.isDropPlayerItemModded = this.beforeDropPlayerItemHooks != null || this.overrideDropPlayerItemHooks != null || this.afterDropPlayerItemHooks != null;
        this.beforeDropPlayerItemWithRandomChoiceHooks = this.create(beforeDropPlayerItemWithRandomChoiceHookTypes);
        this.overrideDropPlayerItemWithRandomChoiceHooks = this.create(overrideDropPlayerItemWithRandomChoiceHookTypes);
        this.afterDropPlayerItemWithRandomChoiceHooks = this.create(afterDropPlayerItemWithRandomChoiceHookTypes);
        this.isDropPlayerItemWithRandomChoiceModded = this.beforeDropPlayerItemWithRandomChoiceHooks != null || this.overrideDropPlayerItemWithRandomChoiceHooks != null || this.afterDropPlayerItemWithRandomChoiceHooks != null;
        this.beforeFallHooks = this.create(beforeFallHookTypes);
        this.overrideFallHooks = this.create(overrideFallHookTypes);
        this.afterFallHooks = this.create(afterFallHookTypes);
        this.isFallModded = this.beforeFallHooks != null || this.overrideFallHooks != null || this.afterFallHooks != null;
        this.beforeGetBrightnessHooks = this.create(beforeGetBrightnessHookTypes);
        this.overrideGetBrightnessHooks = this.create(overrideGetBrightnessHookTypes);
        this.afterGetBrightnessHooks = this.create(afterGetBrightnessHookTypes);
        this.isGetBrightnessModded = this.beforeGetBrightnessHooks != null || this.overrideGetBrightnessHooks != null || this.afterGetBrightnessHooks != null;
        this.beforeGetBrightnessForRenderHooks = this.create(beforeGetBrightnessForRenderHookTypes);
        this.overrideGetBrightnessForRenderHooks = this.create(overrideGetBrightnessForRenderHookTypes);
        this.afterGetBrightnessForRenderHooks = this.create(afterGetBrightnessForRenderHookTypes);
        this.isGetBrightnessForRenderModded = this.beforeGetBrightnessForRenderHooks != null || this.overrideGetBrightnessForRenderHooks != null || this.afterGetBrightnessForRenderHooks != null;
        this.beforeGetCurrentPlayerStrVsBlockHooks = this.create(beforeGetCurrentPlayerStrVsBlockHookTypes);
        this.overrideGetCurrentPlayerStrVsBlockHooks = this.create(overrideGetCurrentPlayerStrVsBlockHookTypes);
        this.afterGetCurrentPlayerStrVsBlockHooks = this.create(afterGetCurrentPlayerStrVsBlockHookTypes);
        this.isGetCurrentPlayerStrVsBlockModded = this.beforeGetCurrentPlayerStrVsBlockHooks != null || this.overrideGetCurrentPlayerStrVsBlockHooks != null || this.afterGetCurrentPlayerStrVsBlockHooks != null;
        this.beforeGetDistanceSqHooks = this.create(beforeGetDistanceSqHookTypes);
        this.overrideGetDistanceSqHooks = this.create(overrideGetDistanceSqHookTypes);
        this.afterGetDistanceSqHooks = this.create(afterGetDistanceSqHookTypes);
        this.isGetDistanceSqModded = this.beforeGetDistanceSqHooks != null || this.overrideGetDistanceSqHooks != null || this.afterGetDistanceSqHooks != null;
        this.beforeGetDistanceSqToEntityHooks = this.create(beforeGetDistanceSqToEntityHookTypes);
        this.overrideGetDistanceSqToEntityHooks = this.create(overrideGetDistanceSqToEntityHookTypes);
        this.afterGetDistanceSqToEntityHooks = this.create(afterGetDistanceSqToEntityHookTypes);
        this.isGetDistanceSqToEntityModded = this.beforeGetDistanceSqToEntityHooks != null || this.overrideGetDistanceSqToEntityHooks != null || this.afterGetDistanceSqToEntityHooks != null;
        this.beforeGetFOVMultiplierHooks = this.create(beforeGetFOVMultiplierHookTypes);
        this.overrideGetFOVMultiplierHooks = this.create(overrideGetFOVMultiplierHookTypes);
        this.afterGetFOVMultiplierHooks = this.create(afterGetFOVMultiplierHookTypes);
        this.isGetFOVMultiplierModded = this.beforeGetFOVMultiplierHooks != null || this.overrideGetFOVMultiplierHooks != null || this.afterGetFOVMultiplierHooks != null;
        this.beforeGetHurtSoundHooks = this.create(beforeGetHurtSoundHookTypes);
        this.overrideGetHurtSoundHooks = this.create(overrideGetHurtSoundHookTypes);
        this.afterGetHurtSoundHooks = this.create(afterGetHurtSoundHookTypes);
        this.isGetHurtSoundModded = this.beforeGetHurtSoundHooks != null || this.overrideGetHurtSoundHooks != null || this.afterGetHurtSoundHooks != null;
        this.beforeGetItemIconHooks = this.create(beforeGetItemIconHookTypes);
        this.overrideGetItemIconHooks = this.create(overrideGetItemIconHookTypes);
        this.afterGetItemIconHooks = this.create(afterGetItemIconHookTypes);
        this.isGetItemIconModded = this.beforeGetItemIconHooks != null || this.overrideGetItemIconHooks != null || this.afterGetItemIconHooks != null;
        this.beforeGetMaxHealthHooks = this.create(beforeGetMaxHealthHookTypes);
        this.overrideGetMaxHealthHooks = this.create(overrideGetMaxHealthHookTypes);
        this.afterGetMaxHealthHooks = this.create(afterGetMaxHealthHookTypes);
        this.isGetMaxHealthModded = this.beforeGetMaxHealthHooks != null || this.overrideGetMaxHealthHooks != null || this.afterGetMaxHealthHooks != null;
        this.beforeGetSleepTimerHooks = this.create(beforeGetSleepTimerHookTypes);
        this.overrideGetSleepTimerHooks = this.create(overrideGetSleepTimerHookTypes);
        this.afterGetSleepTimerHooks = this.create(afterGetSleepTimerHookTypes);
        this.isGetSleepTimerModded = this.beforeGetSleepTimerHooks != null || this.overrideGetSleepTimerHooks != null || this.afterGetSleepTimerHooks != null;
        this.beforeGetSpeedModifierHooks = this.create(beforeGetSpeedModifierHookTypes);
        this.overrideGetSpeedModifierHooks = this.create(overrideGetSpeedModifierHookTypes);
        this.afterGetSpeedModifierHooks = this.create(afterGetSpeedModifierHookTypes);
        this.isGetSpeedModifierModded = this.beforeGetSpeedModifierHooks != null || this.overrideGetSpeedModifierHooks != null || this.afterGetSpeedModifierHooks != null;
        this.beforeHandleLavaMovementHooks = this.create(beforeHandleLavaMovementHookTypes);
        this.overrideHandleLavaMovementHooks = this.create(overrideHandleLavaMovementHookTypes);
        this.afterHandleLavaMovementHooks = this.create(afterHandleLavaMovementHookTypes);
        this.isHandleLavaMovementModded = this.beforeHandleLavaMovementHooks != null || this.overrideHandleLavaMovementHooks != null || this.afterHandleLavaMovementHooks != null;
        this.beforeHandleWaterMovementHooks = this.create(beforeHandleWaterMovementHookTypes);
        this.overrideHandleWaterMovementHooks = this.create(overrideHandleWaterMovementHookTypes);
        this.afterHandleWaterMovementHooks = this.create(afterHandleWaterMovementHookTypes);
        this.isHandleWaterMovementModded = this.beforeHandleWaterMovementHooks != null || this.overrideHandleWaterMovementHooks != null || this.afterHandleWaterMovementHooks != null;
        this.beforeHealHooks = this.create(beforeHealHookTypes);
        this.overrideHealHooks = this.create(overrideHealHookTypes);
        this.afterHealHooks = this.create(afterHealHookTypes);
        this.isHealModded = this.beforeHealHooks != null || this.overrideHealHooks != null || this.afterHealHooks != null;
        this.beforeInteractHooks = this.create(beforeInteractHookTypes);
        this.overrideInteractHooks = this.create(overrideInteractHookTypes);
        this.afterInteractHooks = this.create(afterInteractHookTypes);
        this.isInteractModded = this.beforeInteractHooks != null || this.overrideInteractHooks != null || this.afterInteractHooks != null;
        this.beforeIsEntityInsideOpaqueBlockHooks = this.create(beforeIsEntityInsideOpaqueBlockHookTypes);
        this.overrideIsEntityInsideOpaqueBlockHooks = this.create(overrideIsEntityInsideOpaqueBlockHookTypes);
        this.afterIsEntityInsideOpaqueBlockHooks = this.create(afterIsEntityInsideOpaqueBlockHookTypes);
        this.isIsEntityInsideOpaqueBlockModded = this.beforeIsEntityInsideOpaqueBlockHooks != null || this.overrideIsEntityInsideOpaqueBlockHooks != null || this.afterIsEntityInsideOpaqueBlockHooks != null;
        this.beforeIsInWaterHooks = this.create(beforeIsInWaterHookTypes);
        this.overrideIsInWaterHooks = this.create(overrideIsInWaterHookTypes);
        this.afterIsInWaterHooks = this.create(afterIsInWaterHookTypes);
        this.isIsInWaterModded = this.beforeIsInWaterHooks != null || this.overrideIsInWaterHooks != null || this.afterIsInWaterHooks != null;
        this.beforeIsInsideOfMaterialHooks = this.create(beforeIsInsideOfMaterialHookTypes);
        this.overrideIsInsideOfMaterialHooks = this.create(overrideIsInsideOfMaterialHookTypes);
        this.afterIsInsideOfMaterialHooks = this.create(afterIsInsideOfMaterialHookTypes);
        this.isIsInsideOfMaterialModded = this.beforeIsInsideOfMaterialHooks != null || this.overrideIsInsideOfMaterialHooks != null || this.afterIsInsideOfMaterialHooks != null;
        this.beforeIsOnLadderHooks = this.create(beforeIsOnLadderHookTypes);
        this.overrideIsOnLadderHooks = this.create(overrideIsOnLadderHookTypes);
        this.afterIsOnLadderHooks = this.create(afterIsOnLadderHookTypes);
        this.isIsOnLadderModded = this.beforeIsOnLadderHooks != null || this.overrideIsOnLadderHooks != null || this.afterIsOnLadderHooks != null;
        this.beforeIsPlayerSleepingHooks = this.create(beforeIsPlayerSleepingHookTypes);
        this.overrideIsPlayerSleepingHooks = this.create(overrideIsPlayerSleepingHookTypes);
        this.afterIsPlayerSleepingHooks = this.create(afterIsPlayerSleepingHookTypes);
        this.isIsPlayerSleepingModded = this.beforeIsPlayerSleepingHooks != null || this.overrideIsPlayerSleepingHooks != null || this.afterIsPlayerSleepingHooks != null;
        this.beforeIsSneakingHooks = this.create(beforeIsSneakingHookTypes);
        this.overrideIsSneakingHooks = this.create(overrideIsSneakingHookTypes);
        this.afterIsSneakingHooks = this.create(afterIsSneakingHookTypes);
        this.isIsSneakingModded = this.beforeIsSneakingHooks != null || this.overrideIsSneakingHooks != null || this.afterIsSneakingHooks != null;
        this.beforeIsSprintingHooks = this.create(beforeIsSprintingHookTypes);
        this.overrideIsSprintingHooks = this.create(overrideIsSprintingHookTypes);
        this.afterIsSprintingHooks = this.create(afterIsSprintingHookTypes);
        this.isIsSprintingModded = this.beforeIsSprintingHooks != null || this.overrideIsSprintingHooks != null || this.afterIsSprintingHooks != null;
        this.beforeJumpHooks = this.create(beforeJumpHookTypes);
        this.overrideJumpHooks = this.create(overrideJumpHookTypes);
        this.afterJumpHooks = this.create(afterJumpHookTypes);
        this.isJumpModded = this.beforeJumpHooks != null || this.overrideJumpHooks != null || this.afterJumpHooks != null;
        this.beforeKnockBackHooks = this.create(beforeKnockBackHookTypes);
        this.overrideKnockBackHooks = this.create(overrideKnockBackHookTypes);
        this.afterKnockBackHooks = this.create(afterKnockBackHookTypes);
        this.isKnockBackModded = this.beforeKnockBackHooks != null || this.overrideKnockBackHooks != null || this.afterKnockBackHooks != null;
        this.beforeMoveEntityHooks = this.create(beforeMoveEntityHookTypes);
        this.overrideMoveEntityHooks = this.create(overrideMoveEntityHookTypes);
        this.afterMoveEntityHooks = this.create(afterMoveEntityHookTypes);
        this.isMoveEntityModded = this.beforeMoveEntityHooks != null || this.overrideMoveEntityHooks != null || this.afterMoveEntityHooks != null;
        this.beforeMoveEntityWithHeadingHooks = this.create(beforeMoveEntityWithHeadingHookTypes);
        this.overrideMoveEntityWithHeadingHooks = this.create(overrideMoveEntityWithHeadingHookTypes);
        this.afterMoveEntityWithHeadingHooks = this.create(afterMoveEntityWithHeadingHookTypes);
        this.isMoveEntityWithHeadingModded = this.beforeMoveEntityWithHeadingHooks != null || this.overrideMoveEntityWithHeadingHooks != null || this.afterMoveEntityWithHeadingHooks != null;
        this.beforeMoveFlyingHooks = this.create(beforeMoveFlyingHookTypes);
        this.overrideMoveFlyingHooks = this.create(overrideMoveFlyingHookTypes);
        this.afterMoveFlyingHooks = this.create(afterMoveFlyingHookTypes);
        this.isMoveFlyingModded = this.beforeMoveFlyingHooks != null || this.overrideMoveFlyingHooks != null || this.afterMoveFlyingHooks != null;
        this.beforeOnDeathHooks = this.create(beforeOnDeathHookTypes);
        this.overrideOnDeathHooks = this.create(overrideOnDeathHookTypes);
        this.afterOnDeathHooks = this.create(afterOnDeathHookTypes);
        this.isOnDeathModded = this.beforeOnDeathHooks != null || this.overrideOnDeathHooks != null || this.afterOnDeathHooks != null;
        this.beforeOnLivingUpdateHooks = this.create(beforeOnLivingUpdateHookTypes);
        this.overrideOnLivingUpdateHooks = this.create(overrideOnLivingUpdateHookTypes);
        this.afterOnLivingUpdateHooks = this.create(afterOnLivingUpdateHookTypes);
        this.isOnLivingUpdateModded = this.beforeOnLivingUpdateHooks != null || this.overrideOnLivingUpdateHooks != null || this.afterOnLivingUpdateHooks != null;
        this.beforeOnKillEntityHooks = this.create(beforeOnKillEntityHookTypes);
        this.overrideOnKillEntityHooks = this.create(overrideOnKillEntityHookTypes);
        this.afterOnKillEntityHooks = this.create(afterOnKillEntityHookTypes);
        this.isOnKillEntityModded = this.beforeOnKillEntityHooks != null || this.overrideOnKillEntityHooks != null || this.afterOnKillEntityHooks != null;
        this.beforeOnStruckByLightningHooks = this.create(beforeOnStruckByLightningHookTypes);
        this.overrideOnStruckByLightningHooks = this.create(overrideOnStruckByLightningHookTypes);
        this.afterOnStruckByLightningHooks = this.create(afterOnStruckByLightningHookTypes);
        this.isOnStruckByLightningModded = this.beforeOnStruckByLightningHooks != null || this.overrideOnStruckByLightningHooks != null || this.afterOnStruckByLightningHooks != null;
        this.beforeOnUpdateHooks = this.create(beforeOnUpdateHookTypes);
        this.overrideOnUpdateHooks = this.create(overrideOnUpdateHookTypes);
        this.afterOnUpdateHooks = this.create(afterOnUpdateHookTypes);
        this.isOnUpdateModded = this.beforeOnUpdateHooks != null || this.overrideOnUpdateHooks != null || this.afterOnUpdateHooks != null;
        this.beforePlayStepSoundHooks = this.create(beforePlayStepSoundHookTypes);
        this.overridePlayStepSoundHooks = this.create(overridePlayStepSoundHookTypes);
        this.afterPlayStepSoundHooks = this.create(afterPlayStepSoundHookTypes);
        this.isPlayStepSoundModded = this.beforePlayStepSoundHooks != null || this.overridePlayStepSoundHooks != null || this.afterPlayStepSoundHooks != null;
        this.beforePushOutOfBlocksHooks = this.create(beforePushOutOfBlocksHookTypes);
        this.overridePushOutOfBlocksHooks = this.create(overridePushOutOfBlocksHookTypes);
        this.afterPushOutOfBlocksHooks = this.create(afterPushOutOfBlocksHookTypes);
        this.isPushOutOfBlocksModded = this.beforePushOutOfBlocksHooks != null || this.overridePushOutOfBlocksHooks != null || this.afterPushOutOfBlocksHooks != null;
        this.beforeRayTraceHooks = this.create(beforeRayTraceHookTypes);
        this.overrideRayTraceHooks = this.create(overrideRayTraceHookTypes);
        this.afterRayTraceHooks = this.create(afterRayTraceHookTypes);
        this.isRayTraceModded = this.beforeRayTraceHooks != null || this.overrideRayTraceHooks != null || this.afterRayTraceHooks != null;
        this.beforeReadEntityFromNBTHooks = this.create(beforeReadEntityFromNBTHookTypes);
        this.overrideReadEntityFromNBTHooks = this.create(overrideReadEntityFromNBTHookTypes);
        this.afterReadEntityFromNBTHooks = this.create(afterReadEntityFromNBTHookTypes);
        this.isReadEntityFromNBTModded = this.beforeReadEntityFromNBTHooks != null || this.overrideReadEntityFromNBTHooks != null || this.afterReadEntityFromNBTHooks != null;
        this.beforeRespawnPlayerHooks = this.create(beforeRespawnPlayerHookTypes);
        this.overrideRespawnPlayerHooks = this.create(overrideRespawnPlayerHookTypes);
        this.afterRespawnPlayerHooks = this.create(afterRespawnPlayerHookTypes);
        this.isRespawnPlayerModded = this.beforeRespawnPlayerHooks != null || this.overrideRespawnPlayerHooks != null || this.afterRespawnPlayerHooks != null;
        this.beforeSetDeadHooks = this.create(beforeSetDeadHookTypes);
        this.overrideSetDeadHooks = this.create(overrideSetDeadHookTypes);
        this.afterSetDeadHooks = this.create(afterSetDeadHookTypes);
        this.isSetDeadModded = this.beforeSetDeadHooks != null || this.overrideSetDeadHooks != null || this.afterSetDeadHooks != null;
        this.beforeSetPositionAndRotationHooks = this.create(beforeSetPositionAndRotationHookTypes);
        this.overrideSetPositionAndRotationHooks = this.create(overrideSetPositionAndRotationHookTypes);
        this.afterSetPositionAndRotationHooks = this.create(afterSetPositionAndRotationHookTypes);
        this.isSetPositionAndRotationModded = this.beforeSetPositionAndRotationHooks != null || this.overrideSetPositionAndRotationHooks != null || this.afterSetPositionAndRotationHooks != null;
        this.beforeSleepInBedAtHooks = this.create(beforeSleepInBedAtHookTypes);
        this.overrideSleepInBedAtHooks = this.create(overrideSleepInBedAtHookTypes);
        this.afterSleepInBedAtHooks = this.create(afterSleepInBedAtHookTypes);
        this.isSleepInBedAtModded = this.beforeSleepInBedAtHooks != null || this.overrideSleepInBedAtHooks != null || this.afterSleepInBedAtHooks != null;
        this.beforeSwingItemHooks = this.create(beforeSwingItemHookTypes);
        this.overrideSwingItemHooks = this.create(overrideSwingItemHookTypes);
        this.afterSwingItemHooks = this.create(afterSwingItemHookTypes);
        this.isSwingItemModded = this.beforeSwingItemHooks != null || this.overrideSwingItemHooks != null || this.afterSwingItemHooks != null;
        this.beforeUpdateCloakHooks = this.create(beforeUpdateCloakHookTypes);
        this.overrideUpdateCloakHooks = this.create(overrideUpdateCloakHookTypes);
        this.afterUpdateCloakHooks = this.create(afterUpdateCloakHookTypes);
        this.isUpdateCloakModded = this.beforeUpdateCloakHooks != null || this.overrideUpdateCloakHooks != null || this.afterUpdateCloakHooks != null;
        this.beforeUpdateEntityActionStateHooks = this.create(beforeUpdateEntityActionStateHookTypes);
        this.overrideUpdateEntityActionStateHooks = this.create(overrideUpdateEntityActionStateHookTypes);
        this.afterUpdateEntityActionStateHooks = this.create(afterUpdateEntityActionStateHookTypes);
        this.isUpdateEntityActionStateModded = this.beforeUpdateEntityActionStateHooks != null || this.overrideUpdateEntityActionStateHooks != null || this.afterUpdateEntityActionStateHooks != null;
        this.beforeUpdateRiddenHooks = this.create(beforeUpdateRiddenHookTypes);
        this.overrideUpdateRiddenHooks = this.create(overrideUpdateRiddenHookTypes);
        this.afterUpdateRiddenHooks = this.create(afterUpdateRiddenHookTypes);
        this.isUpdateRiddenModded = this.beforeUpdateRiddenHooks != null || this.overrideUpdateRiddenHooks != null || this.afterUpdateRiddenHooks != null;
        this.beforeWriteEntityToNBTHooks = this.create(beforeWriteEntityToNBTHookTypes);
        this.overrideWriteEntityToNBTHooks = this.create(overrideWriteEntityToNBTHookTypes);
        this.afterWriteEntityToNBTHooks = this.create(afterWriteEntityToNBTHookTypes);
        this.isWriteEntityToNBTModded = this.beforeWriteEntityToNBTHooks != null || this.overrideWriteEntityToNBTHooks != null || this.afterWriteEntityToNBTHooks != null;
    }

    private PlayerBase[] create(List var1)
    {
        if (var1.isEmpty())
        {
            return null;
        }
        else
        {
            PlayerBase[] var2 = new PlayerBase[var1.size()];

            for (int var3 = 0; var3 < var2.length; ++var3)
            {
                var2[var3] = this.getPlayerBase((String)var1.get(var3));
            }

            return var2;
        }
    }

    private void beforeLocalConstructing(Minecraft var1, World var2, Session var3, int var4)
    {
        if (this.beforeLocalConstructingHooks != null)
        {
            for (int var5 = this.beforeLocalConstructingHooks.length - 1; var5 >= 0; --var5)
            {
                this.beforeLocalConstructingHooks[var5].beforeLocalConstructing(var1, var2, var3, var4);
            }
        }
    }

    private void afterLocalConstructing(Minecraft var1, World var2, Session var3, int var4)
    {
        if (this.afterLocalConstructingHooks != null)
        {
            for (int var5 = 0; var5 < this.afterLocalConstructingHooks.length; ++var5)
            {
                this.afterLocalConstructingHooks[var5].afterLocalConstructing(var1, var2, var3, var4);
            }
        }
    }

    public PlayerBase getPlayerBase(String var1)
    {
        return (PlayerBase)this.allBaseObjects.get(var1);
    }

    public Set getPlayerBaseIds()
    {
        return this.unmodifiableAllBaseIds;
    }

    public static void addExhaustion(EntityPlayerSP var0, float var1)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.addExhaustion(var1);
        }
        else
        {
            var0.localAddExhaustion(var1);
        }
    }

    private void addExhaustion(float var1)
    {
        int var2;

        if (this.beforeAddExhaustionHooks != null)
        {
            for (var2 = this.beforeAddExhaustionHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeAddExhaustionHooks[var2].beforeAddExhaustion(var1);
            }
        }

        if (this.overrideAddExhaustionHooks != null)
        {
            this.overrideAddExhaustionHooks[this.overrideAddExhaustionHooks.length - 1].addExhaustion(var1);
        }
        else
        {
            this.player.localAddExhaustion(var1);
        }

        if (this.afterAddExhaustionHooks != null)
        {
            for (var2 = 0; var2 < this.afterAddExhaustionHooks.length; ++var2)
            {
                this.afterAddExhaustionHooks[var2].afterAddExhaustion(var1);
            }
        }
    }

    protected PlayerBase GetOverwrittenAddExhaustion(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideAddExhaustionHooks.length; ++var2)
        {
            if (this.overrideAddExhaustionHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideAddExhaustionHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void addMovementStat(EntityPlayerSP var0, double var1, double var3, double var5)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.addMovementStat(var1, var3, var5);
        }
        else
        {
            var0.localAddMovementStat(var1, var3, var5);
        }
    }

    private void addMovementStat(double var1, double var3, double var5)
    {
        int var7;

        if (this.beforeAddMovementStatHooks != null)
        {
            for (var7 = this.beforeAddMovementStatHooks.length - 1; var7 >= 0; --var7)
            {
                this.beforeAddMovementStatHooks[var7].beforeAddMovementStat(var1, var3, var5);
            }
        }

        if (this.overrideAddMovementStatHooks != null)
        {
            this.overrideAddMovementStatHooks[this.overrideAddMovementStatHooks.length - 1].addMovementStat(var1, var3, var5);
        }
        else
        {
            this.player.localAddMovementStat(var1, var3, var5);
        }

        if (this.afterAddMovementStatHooks != null)
        {
            for (var7 = 0; var7 < this.afterAddMovementStatHooks.length; ++var7)
            {
                this.afterAddMovementStatHooks[var7].afterAddMovementStat(var1, var3, var5);
            }
        }
    }

    protected PlayerBase GetOverwrittenAddMovementStat(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideAddMovementStatHooks.length; ++var2)
        {
            if (this.overrideAddMovementStatHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideAddMovementStatHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void addStat(EntityPlayerSP var0, StatBase var1, int var2)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.addStat(var1, var2);
        }
        else
        {
            var0.localAddStat(var1, var2);
        }
    }

    private void addStat(StatBase var1, int var2)
    {
        int var3;

        if (this.beforeAddStatHooks != null)
        {
            for (var3 = this.beforeAddStatHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeAddStatHooks[var3].beforeAddStat(var1, var2);
            }
        }

        if (this.overrideAddStatHooks != null)
        {
            this.overrideAddStatHooks[this.overrideAddStatHooks.length - 1].addStat(var1, var2);
        }
        else
        {
            this.player.localAddStat(var1, var2);
        }

        if (this.afterAddStatHooks != null)
        {
            for (var3 = 0; var3 < this.afterAddStatHooks.length; ++var3)
            {
                this.afterAddStatHooks[var3].afterAddStat(var1, var2);
            }
        }
    }

    protected PlayerBase GetOverwrittenAddStat(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideAddStatHooks.length; ++var2)
        {
            if (this.overrideAddStatHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideAddStatHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean attackEntityFrom(EntityPlayerSP var0, DamageSource var1, int var2)
    {
        boolean var3;

        if (var0.playerAPI != null)
        {
            var3 = var0.playerAPI.attackEntityFrom(var1, var2);
        }
        else
        {
            var3 = var0.localAttackEntityFrom(var1, var2);
        }

        return var3;
    }

    private boolean attackEntityFrom(DamageSource var1, int var2)
    {
        if (this.beforeAttackEntityFromHooks != null)
        {
            for (int var3 = this.beforeAttackEntityFromHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeAttackEntityFromHooks[var3].beforeAttackEntityFrom(var1, var2);
            }
        }

        boolean var5;

        if (this.overrideAttackEntityFromHooks != null)
        {
            var5 = this.overrideAttackEntityFromHooks[this.overrideAttackEntityFromHooks.length - 1].attackEntityFrom(var1, var2);
        }
        else
        {
            var5 = this.player.localAttackEntityFrom(var1, var2);
        }

        if (this.afterAttackEntityFromHooks != null)
        {
            for (int var4 = 0; var4 < this.afterAttackEntityFromHooks.length; ++var4)
            {
                this.afterAttackEntityFromHooks[var4].afterAttackEntityFrom(var1, var2);
            }
        }

        return var5;
    }

    protected PlayerBase GetOverwrittenAttackEntityFrom(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideAttackEntityFromHooks.length; ++var2)
        {
            if (this.overrideAttackEntityFromHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideAttackEntityFromHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void alertWolves(EntityPlayerSP var0, EntityLiving var1, boolean var2)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.alertWolves(var1, var2);
        }
        else
        {
            var0.localAlertWolves(var1, var2);
        }
    }

    private void alertWolves(EntityLiving var1, boolean var2)
    {
        int var3;

        if (this.beforeAlertWolvesHooks != null)
        {
            for (var3 = this.beforeAlertWolvesHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeAlertWolvesHooks[var3].beforeAlertWolves(var1, var2);
            }
        }

        if (this.overrideAlertWolvesHooks != null)
        {
            this.overrideAlertWolvesHooks[this.overrideAlertWolvesHooks.length - 1].alertWolves(var1, var2);
        }
        else
        {
            this.player.localAlertWolves(var1, var2);
        }

        if (this.afterAlertWolvesHooks != null)
        {
            for (var3 = 0; var3 < this.afterAlertWolvesHooks.length; ++var3)
            {
                this.afterAlertWolvesHooks[var3].afterAlertWolves(var1, var2);
            }
        }
    }

    protected PlayerBase GetOverwrittenAlertWolves(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideAlertWolvesHooks.length; ++var2)
        {
            if (this.overrideAlertWolvesHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideAlertWolvesHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void attackTargetEntityWithCurrentItem(EntityPlayerSP var0, Entity var1)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.attackTargetEntityWithCurrentItem(var1);
        }
        else
        {
            var0.localAttackTargetEntityWithCurrentItem(var1);
        }
    }

    private void attackTargetEntityWithCurrentItem(Entity var1)
    {
        int var2;

        if (this.beforeAttackTargetEntityWithCurrentItemHooks != null)
        {
            for (var2 = this.beforeAttackTargetEntityWithCurrentItemHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeAttackTargetEntityWithCurrentItemHooks[var2].beforeAttackTargetEntityWithCurrentItem(var1);
            }
        }

        if (this.overrideAttackTargetEntityWithCurrentItemHooks != null)
        {
            this.overrideAttackTargetEntityWithCurrentItemHooks[this.overrideAttackTargetEntityWithCurrentItemHooks.length - 1].attackTargetEntityWithCurrentItem(var1);
        }
        else
        {
            this.player.localAttackTargetEntityWithCurrentItem(var1);
        }

        if (this.afterAttackTargetEntityWithCurrentItemHooks != null)
        {
            for (var2 = 0; var2 < this.afterAttackTargetEntityWithCurrentItemHooks.length; ++var2)
            {
                this.afterAttackTargetEntityWithCurrentItemHooks[var2].afterAttackTargetEntityWithCurrentItem(var1);
            }
        }
    }

    protected PlayerBase GetOverwrittenAttackTargetEntityWithCurrentItem(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideAttackTargetEntityWithCurrentItemHooks.length; ++var2)
        {
            if (this.overrideAttackTargetEntityWithCurrentItemHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideAttackTargetEntityWithCurrentItemHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean canBreatheUnderwater(EntityPlayerSP var0)
    {
        boolean var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.canBreatheUnderwater();
        }
        else
        {
            var1 = var0.localCanBreatheUnderwater();
        }

        return var1;
    }

    private boolean canBreatheUnderwater()
    {
        if (this.beforeCanBreatheUnderwaterHooks != null)
        {
            for (int var1 = this.beforeCanBreatheUnderwaterHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeCanBreatheUnderwaterHooks[var1].beforeCanBreatheUnderwater();
            }
        }

        boolean var3;

        if (this.overrideCanBreatheUnderwaterHooks != null)
        {
            var3 = this.overrideCanBreatheUnderwaterHooks[this.overrideCanBreatheUnderwaterHooks.length - 1].canBreatheUnderwater();
        }
        else
        {
            var3 = this.player.localCanBreatheUnderwater();
        }

        if (this.afterCanBreatheUnderwaterHooks != null)
        {
            for (int var2 = 0; var2 < this.afterCanBreatheUnderwaterHooks.length; ++var2)
            {
                this.afterCanBreatheUnderwaterHooks[var2].afterCanBreatheUnderwater();
            }
        }

        return var3;
    }

    protected PlayerBase GetOverwrittenCanBreatheUnderwater(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideCanBreatheUnderwaterHooks.length; ++var2)
        {
            if (this.overrideCanBreatheUnderwaterHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideCanBreatheUnderwaterHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean canHarvestBlock(EntityPlayerSP var0, Block var1)
    {
        boolean var2;

        if (var0.playerAPI != null)
        {
            var2 = var0.playerAPI.canHarvestBlock(var1);
        }
        else
        {
            var2 = var0.localCanHarvestBlock(var1);
        }

        return var2;
    }

    private boolean canHarvestBlock(Block var1)
    {
        if (this.beforeCanHarvestBlockHooks != null)
        {
            for (int var2 = this.beforeCanHarvestBlockHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeCanHarvestBlockHooks[var2].beforeCanHarvestBlock(var1);
            }
        }

        boolean var4;

        if (this.overrideCanHarvestBlockHooks != null)
        {
            var4 = this.overrideCanHarvestBlockHooks[this.overrideCanHarvestBlockHooks.length - 1].canHarvestBlock(var1);
        }
        else
        {
            var4 = this.player.localCanHarvestBlock(var1);
        }

        if (this.afterCanHarvestBlockHooks != null)
        {
            for (int var3 = 0; var3 < this.afterCanHarvestBlockHooks.length; ++var3)
            {
                this.afterCanHarvestBlockHooks[var3].afterCanHarvestBlock(var1);
            }
        }

        return var4;
    }

    protected PlayerBase GetOverwrittenCanHarvestBlock(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideCanHarvestBlockHooks.length; ++var2)
        {
            if (this.overrideCanHarvestBlockHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideCanHarvestBlockHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean canPlayerEdit(EntityPlayerSP var0, int var1, int var2, int var3, int var4, ItemStack var5)
    {
        boolean var6;

        if (var0.playerAPI != null)
        {
            var6 = var0.playerAPI.canPlayerEdit(var1, var2, var3, var4, var5);
        }
        else
        {
            var6 = var0.localCanPlayerEdit(var1, var2, var3, var4, var5);
        }

        return var6;
    }

    private boolean canPlayerEdit(int var1, int var2, int var3, int var4, ItemStack var5)
    {
        if (this.beforeCanPlayerEditHooks != null)
        {
            for (int var6 = this.beforeCanPlayerEditHooks.length - 1; var6 >= 0; --var6)
            {
                this.beforeCanPlayerEditHooks[var6].beforeCanPlayerEdit(var1, var2, var3, var4, var5);
            }
        }

        boolean var8;

        if (this.overrideCanPlayerEditHooks != null)
        {
            var8 = this.overrideCanPlayerEditHooks[this.overrideCanPlayerEditHooks.length - 1].canPlayerEdit(var1, var2, var3, var4, var5);
        }
        else
        {
            var8 = this.player.localCanPlayerEdit(var1, var2, var3, var4, var5);
        }

        if (this.afterCanPlayerEditHooks != null)
        {
            for (int var7 = 0; var7 < this.afterCanPlayerEditHooks.length; ++var7)
            {
                this.afterCanPlayerEditHooks[var7].afterCanPlayerEdit(var1, var2, var3, var4, var5);
            }
        }

        return var8;
    }

    protected PlayerBase GetOverwrittenCanPlayerEdit(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideCanPlayerEditHooks.length; ++var2)
        {
            if (this.overrideCanPlayerEditHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideCanPlayerEditHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean canTriggerWalking(EntityPlayerSP var0)
    {
        boolean var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.canTriggerWalking();
        }
        else
        {
            var1 = var0.localCanTriggerWalking();
        }

        return var1;
    }

    private boolean canTriggerWalking()
    {
        if (this.beforeCanTriggerWalkingHooks != null)
        {
            for (int var1 = this.beforeCanTriggerWalkingHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeCanTriggerWalkingHooks[var1].beforeCanTriggerWalking();
            }
        }

        boolean var3;

        if (this.overrideCanTriggerWalkingHooks != null)
        {
            var3 = this.overrideCanTriggerWalkingHooks[this.overrideCanTriggerWalkingHooks.length - 1].canTriggerWalking();
        }
        else
        {
            var3 = this.player.localCanTriggerWalking();
        }

        if (this.afterCanTriggerWalkingHooks != null)
        {
            for (int var2 = 0; var2 < this.afterCanTriggerWalkingHooks.length; ++var2)
            {
                this.afterCanTriggerWalkingHooks[var2].afterCanTriggerWalking();
            }
        }

        return var3;
    }

    protected PlayerBase GetOverwrittenCanTriggerWalking(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideCanTriggerWalkingHooks.length; ++var2)
        {
            if (this.overrideCanTriggerWalkingHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideCanTriggerWalkingHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void closeScreen(EntityPlayerSP var0)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.closeScreen();
        }
        else
        {
            var0.localCloseScreen();
        }
    }

    private void closeScreen()
    {
        int var1;

        if (this.beforeCloseScreenHooks != null)
        {
            for (var1 = this.beforeCloseScreenHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeCloseScreenHooks[var1].beforeCloseScreen();
            }
        }

        if (this.overrideCloseScreenHooks != null)
        {
            this.overrideCloseScreenHooks[this.overrideCloseScreenHooks.length - 1].closeScreen();
        }
        else
        {
            this.player.localCloseScreen();
        }

        if (this.afterCloseScreenHooks != null)
        {
            for (var1 = 0; var1 < this.afterCloseScreenHooks.length; ++var1)
            {
                this.afterCloseScreenHooks[var1].afterCloseScreen();
            }
        }
    }

    protected PlayerBase GetOverwrittenCloseScreen(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideCloseScreenHooks.length; ++var2)
        {
            if (this.overrideCloseScreenHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideCloseScreenHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void damageEntity(EntityPlayerSP var0, DamageSource var1, int var2)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.damageEntity(var1, var2);
        }
        else
        {
            var0.localDamageEntity(var1, var2);
        }
    }

    private void damageEntity(DamageSource var1, int var2)
    {
        int var3;

        if (this.beforeDamageEntityHooks != null)
        {
            for (var3 = this.beforeDamageEntityHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeDamageEntityHooks[var3].beforeDamageEntity(var1, var2);
            }
        }

        if (this.overrideDamageEntityHooks != null)
        {
            this.overrideDamageEntityHooks[this.overrideDamageEntityHooks.length - 1].damageEntity(var1, var2);
        }
        else
        {
            this.player.localDamageEntity(var1, var2);
        }

        if (this.afterDamageEntityHooks != null)
        {
            for (var3 = 0; var3 < this.afterDamageEntityHooks.length; ++var3)
            {
                this.afterDamageEntityHooks[var3].afterDamageEntity(var1, var2);
            }
        }
    }

    protected PlayerBase GetOverwrittenDamageEntity(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideDamageEntityHooks.length; ++var2)
        {
            if (this.overrideDamageEntityHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideDamageEntityHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void displayGUIBrewingStand(EntityPlayerSP var0, TileEntityBrewingStand var1)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.displayGUIBrewingStand(var1);
        }
        else
        {
            var0.localDisplayGUIBrewingStand(var1);
        }
    }

    private void displayGUIBrewingStand(TileEntityBrewingStand var1)
    {
        int var2;

        if (this.beforeDisplayGUIBrewingStandHooks != null)
        {
            for (var2 = this.beforeDisplayGUIBrewingStandHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeDisplayGUIBrewingStandHooks[var2].beforeDisplayGUIBrewingStand(var1);
            }
        }

        if (this.overrideDisplayGUIBrewingStandHooks != null)
        {
            this.overrideDisplayGUIBrewingStandHooks[this.overrideDisplayGUIBrewingStandHooks.length - 1].displayGUIBrewingStand(var1);
        }
        else
        {
            this.player.localDisplayGUIBrewingStand(var1);
        }

        if (this.afterDisplayGUIBrewingStandHooks != null)
        {
            for (var2 = 0; var2 < this.afterDisplayGUIBrewingStandHooks.length; ++var2)
            {
                this.afterDisplayGUIBrewingStandHooks[var2].afterDisplayGUIBrewingStand(var1);
            }
        }
    }

    protected PlayerBase GetOverwrittenDisplayGUIBrewingStand(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideDisplayGUIBrewingStandHooks.length; ++var2)
        {
            if (this.overrideDisplayGUIBrewingStandHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideDisplayGUIBrewingStandHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void displayGUIChest(EntityPlayerSP var0, IInventory var1)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.displayGUIChest(var1);
        }
        else
        {
            var0.localDisplayGUIChest(var1);
        }
    }

    private void displayGUIChest(IInventory var1)
    {
        int var2;

        if (this.beforeDisplayGUIChestHooks != null)
        {
            for (var2 = this.beforeDisplayGUIChestHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeDisplayGUIChestHooks[var2].beforeDisplayGUIChest(var1);
            }
        }

        if (this.overrideDisplayGUIChestHooks != null)
        {
            this.overrideDisplayGUIChestHooks[this.overrideDisplayGUIChestHooks.length - 1].displayGUIChest(var1);
        }
        else
        {
            this.player.localDisplayGUIChest(var1);
        }

        if (this.afterDisplayGUIChestHooks != null)
        {
            for (var2 = 0; var2 < this.afterDisplayGUIChestHooks.length; ++var2)
            {
                this.afterDisplayGUIChestHooks[var2].afterDisplayGUIChest(var1);
            }
        }
    }

    protected PlayerBase GetOverwrittenDisplayGUIChest(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideDisplayGUIChestHooks.length; ++var2)
        {
            if (this.overrideDisplayGUIChestHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideDisplayGUIChestHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void displayGUIDispenser(EntityPlayerSP var0, TileEntityDispenser var1)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.displayGUIDispenser(var1);
        }
        else
        {
            var0.localDisplayGUIDispenser(var1);
        }
    }

    private void displayGUIDispenser(TileEntityDispenser var1)
    {
        int var2;

        if (this.beforeDisplayGUIDispenserHooks != null)
        {
            for (var2 = this.beforeDisplayGUIDispenserHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeDisplayGUIDispenserHooks[var2].beforeDisplayGUIDispenser(var1);
            }
        }

        if (this.overrideDisplayGUIDispenserHooks != null)
        {
            this.overrideDisplayGUIDispenserHooks[this.overrideDisplayGUIDispenserHooks.length - 1].displayGUIDispenser(var1);
        }
        else
        {
            this.player.localDisplayGUIDispenser(var1);
        }

        if (this.afterDisplayGUIDispenserHooks != null)
        {
            for (var2 = 0; var2 < this.afterDisplayGUIDispenserHooks.length; ++var2)
            {
                this.afterDisplayGUIDispenserHooks[var2].afterDisplayGUIDispenser(var1);
            }
        }
    }

    protected PlayerBase GetOverwrittenDisplayGUIDispenser(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideDisplayGUIDispenserHooks.length; ++var2)
        {
            if (this.overrideDisplayGUIDispenserHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideDisplayGUIDispenserHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void displayGUIEditSign(EntityPlayerSP var0, TileEntity var1)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.displayGUIEditSign(var1);
        }
        else
        {
            var0.localDisplayGUIEditSign(var1);
        }
    }

    private void displayGUIEditSign(TileEntity var1)
    {
        int var2;

        if (this.beforeDisplayGUIEditSignHooks != null)
        {
            for (var2 = this.beforeDisplayGUIEditSignHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeDisplayGUIEditSignHooks[var2].beforeDisplayGUIEditSign(var1);
            }
        }

        if (this.overrideDisplayGUIEditSignHooks != null)
        {
            this.overrideDisplayGUIEditSignHooks[this.overrideDisplayGUIEditSignHooks.length - 1].displayGUIEditSign(var1);
        }
        else
        {
            this.player.localDisplayGUIEditSign(var1);
        }

        if (this.afterDisplayGUIEditSignHooks != null)
        {
            for (var2 = 0; var2 < this.afterDisplayGUIEditSignHooks.length; ++var2)
            {
                this.afterDisplayGUIEditSignHooks[var2].afterDisplayGUIEditSign(var1);
            }
        }
    }

    protected PlayerBase GetOverwrittenDisplayGUIEditSign(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideDisplayGUIEditSignHooks.length; ++var2)
        {
            if (this.overrideDisplayGUIEditSignHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideDisplayGUIEditSignHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void displayGUIEnchantment(EntityPlayerSP var0, int var1, int var2, int var3)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.displayGUIEnchantment(var1, var2, var3);
        }
        else
        {
            var0.localDisplayGUIEnchantment(var1, var2, var3);
        }
    }

    private void displayGUIEnchantment(int var1, int var2, int var3)
    {
        int var4;

        if (this.beforeDisplayGUIEnchantmentHooks != null)
        {
            for (var4 = this.beforeDisplayGUIEnchantmentHooks.length - 1; var4 >= 0; --var4)
            {
                this.beforeDisplayGUIEnchantmentHooks[var4].beforeDisplayGUIEnchantment(var1, var2, var3);
            }
        }

        if (this.overrideDisplayGUIEnchantmentHooks != null)
        {
            this.overrideDisplayGUIEnchantmentHooks[this.overrideDisplayGUIEnchantmentHooks.length - 1].displayGUIEnchantment(var1, var2, var3);
        }
        else
        {
            this.player.localDisplayGUIEnchantment(var1, var2, var3);
        }

        if (this.afterDisplayGUIEnchantmentHooks != null)
        {
            for (var4 = 0; var4 < this.afterDisplayGUIEnchantmentHooks.length; ++var4)
            {
                this.afterDisplayGUIEnchantmentHooks[var4].afterDisplayGUIEnchantment(var1, var2, var3);
            }
        }
    }

    protected PlayerBase GetOverwrittenDisplayGUIEnchantment(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideDisplayGUIEnchantmentHooks.length; ++var2)
        {
            if (this.overrideDisplayGUIEnchantmentHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideDisplayGUIEnchantmentHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void displayGUIFurnace(EntityPlayerSP var0, TileEntityFurnace var1)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.displayGUIFurnace(var1);
        }
        else
        {
            var0.localDisplayGUIFurnace(var1);
        }
    }

    private void displayGUIFurnace(TileEntityFurnace var1)
    {
        int var2;

        if (this.beforeDisplayGUIFurnaceHooks != null)
        {
            for (var2 = this.beforeDisplayGUIFurnaceHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeDisplayGUIFurnaceHooks[var2].beforeDisplayGUIFurnace(var1);
            }
        }

        if (this.overrideDisplayGUIFurnaceHooks != null)
        {
            this.overrideDisplayGUIFurnaceHooks[this.overrideDisplayGUIFurnaceHooks.length - 1].displayGUIFurnace(var1);
        }
        else
        {
            this.player.localDisplayGUIFurnace(var1);
        }

        if (this.afterDisplayGUIFurnaceHooks != null)
        {
            for (var2 = 0; var2 < this.afterDisplayGUIFurnaceHooks.length; ++var2)
            {
                this.afterDisplayGUIFurnaceHooks[var2].afterDisplayGUIFurnace(var1);
            }
        }
    }

    protected PlayerBase GetOverwrittenDisplayGUIFurnace(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideDisplayGUIFurnaceHooks.length; ++var2)
        {
            if (this.overrideDisplayGUIFurnaceHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideDisplayGUIFurnaceHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void displayGUIWorkbench(EntityPlayerSP var0, int var1, int var2, int var3)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.displayGUIWorkbench(var1, var2, var3);
        }
        else
        {
            var0.localDisplayGUIWorkbench(var1, var2, var3);
        }
    }

    private void displayGUIWorkbench(int var1, int var2, int var3)
    {
        int var4;

        if (this.beforeDisplayGUIWorkbenchHooks != null)
        {
            for (var4 = this.beforeDisplayGUIWorkbenchHooks.length - 1; var4 >= 0; --var4)
            {
                this.beforeDisplayGUIWorkbenchHooks[var4].beforeDisplayGUIWorkbench(var1, var2, var3);
            }
        }

        if (this.overrideDisplayGUIWorkbenchHooks != null)
        {
            this.overrideDisplayGUIWorkbenchHooks[this.overrideDisplayGUIWorkbenchHooks.length - 1].displayGUIWorkbench(var1, var2, var3);
        }
        else
        {
            this.player.localDisplayGUIWorkbench(var1, var2, var3);
        }

        if (this.afterDisplayGUIWorkbenchHooks != null)
        {
            for (var4 = 0; var4 < this.afterDisplayGUIWorkbenchHooks.length; ++var4)
            {
                this.afterDisplayGUIWorkbenchHooks[var4].afterDisplayGUIWorkbench(var1, var2, var3);
            }
        }
    }

    protected PlayerBase GetOverwrittenDisplayGUIWorkbench(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideDisplayGUIWorkbenchHooks.length; ++var2)
        {
            if (this.overrideDisplayGUIWorkbenchHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideDisplayGUIWorkbenchHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static EntityItem dropOneItem(EntityPlayerSP var0, boolean var1)
    {
        EntityItem var2;

        if (var0.playerAPI != null)
        {
            var2 = var0.playerAPI.dropOneItem(var1);
        }
        else
        {
            var2 = var0.localDropOneItem(var1);
        }

        return var2;
    }

    private EntityItem dropOneItem(boolean var1)
    {
        if (this.beforeDropOneItemHooks != null)
        {
            for (int var2 = this.beforeDropOneItemHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeDropOneItemHooks[var2].beforeDropOneItem(var1);
            }
        }

        EntityItem var4;

        if (this.overrideDropOneItemHooks != null)
        {
            var4 = this.overrideDropOneItemHooks[this.overrideDropOneItemHooks.length - 1].dropOneItem(var1);
        }
        else
        {
            var4 = this.player.localDropOneItem(var1);
        }

        if (this.afterDropOneItemHooks != null)
        {
            for (int var3 = 0; var3 < this.afterDropOneItemHooks.length; ++var3)
            {
                this.afterDropOneItemHooks[var3].afterDropOneItem(var1);
            }
        }

        return var4;
    }

    protected PlayerBase GetOverwrittenDropOneItem(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideDropOneItemHooks.length; ++var2)
        {
            if (this.overrideDropOneItemHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideDropOneItemHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static EntityItem dropPlayerItem(EntityPlayerSP var0, ItemStack var1)
    {
        EntityItem var2;

        if (var0.playerAPI != null)
        {
            var2 = var0.playerAPI.dropPlayerItem(var1);
        }
        else
        {
            var2 = var0.localDropPlayerItem(var1);
        }

        return var2;
    }

    private EntityItem dropPlayerItem(ItemStack var1)
    {
        if (this.beforeDropPlayerItemHooks != null)
        {
            for (int var2 = this.beforeDropPlayerItemHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeDropPlayerItemHooks[var2].beforeDropPlayerItem(var1);
            }
        }

        EntityItem var4;

        if (this.overrideDropPlayerItemHooks != null)
        {
            var4 = this.overrideDropPlayerItemHooks[this.overrideDropPlayerItemHooks.length - 1].dropPlayerItem(var1);
        }
        else
        {
            var4 = this.player.localDropPlayerItem(var1);
        }

        if (this.afterDropPlayerItemHooks != null)
        {
            for (int var3 = 0; var3 < this.afterDropPlayerItemHooks.length; ++var3)
            {
                this.afterDropPlayerItemHooks[var3].afterDropPlayerItem(var1);
            }
        }

        return var4;
    }

    protected PlayerBase GetOverwrittenDropPlayerItem(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideDropPlayerItemHooks.length; ++var2)
        {
            if (this.overrideDropPlayerItemHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideDropPlayerItemHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static EntityItem dropPlayerItemWithRandomChoice(EntityPlayerSP var0, ItemStack var1, boolean var2)
    {
        EntityItem var3;

        if (var0.playerAPI != null)
        {
            var3 = var0.playerAPI.dropPlayerItemWithRandomChoice(var1, var2);
        }
        else
        {
            var3 = var0.localDropPlayerItemWithRandomChoice(var1, var2);
        }

        return var3;
    }

    private EntityItem dropPlayerItemWithRandomChoice(ItemStack var1, boolean var2)
    {
        if (this.beforeDropPlayerItemWithRandomChoiceHooks != null)
        {
            for (int var3 = this.beforeDropPlayerItemWithRandomChoiceHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeDropPlayerItemWithRandomChoiceHooks[var3].beforeDropPlayerItemWithRandomChoice(var1, var2);
            }
        }

        EntityItem var5;

        if (this.overrideDropPlayerItemWithRandomChoiceHooks != null)
        {
            var5 = this.overrideDropPlayerItemWithRandomChoiceHooks[this.overrideDropPlayerItemWithRandomChoiceHooks.length - 1].dropPlayerItemWithRandomChoice(var1, var2);
        }
        else
        {
            var5 = this.player.localDropPlayerItemWithRandomChoice(var1, var2);
        }

        if (this.afterDropPlayerItemWithRandomChoiceHooks != null)
        {
            for (int var4 = 0; var4 < this.afterDropPlayerItemWithRandomChoiceHooks.length; ++var4)
            {
                this.afterDropPlayerItemWithRandomChoiceHooks[var4].afterDropPlayerItemWithRandomChoice(var1, var2);
            }
        }

        return var5;
    }

    protected PlayerBase GetOverwrittenDropPlayerItemWithRandomChoice(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideDropPlayerItemWithRandomChoiceHooks.length; ++var2)
        {
            if (this.overrideDropPlayerItemWithRandomChoiceHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideDropPlayerItemWithRandomChoiceHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void fall(EntityPlayerSP var0, float var1)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.fall(var1);
        }
        else
        {
            var0.localFall(var1);
        }
    }

    private void fall(float var1)
    {
        int var2;

        if (this.beforeFallHooks != null)
        {
            for (var2 = this.beforeFallHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeFallHooks[var2].beforeFall(var1);
            }
        }

        if (this.overrideFallHooks != null)
        {
            this.overrideFallHooks[this.overrideFallHooks.length - 1].fall(var1);
        }
        else
        {
            this.player.localFall(var1);
        }

        if (this.afterFallHooks != null)
        {
            for (var2 = 0; var2 < this.afterFallHooks.length; ++var2)
            {
                this.afterFallHooks[var2].afterFall(var1);
            }
        }
    }

    protected PlayerBase GetOverwrittenFall(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideFallHooks.length; ++var2)
        {
            if (this.overrideFallHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideFallHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static float getBrightness(EntityPlayerSP var0, float var1)
    {
        float var2;

        if (var0.playerAPI != null)
        {
            var2 = var0.playerAPI.getBrightness(var1);
        }
        else
        {
            var2 = var0.localGetBrightness(var1);
        }

        return var2;
    }

    private float getBrightness(float var1)
    {
        if (this.beforeGetBrightnessHooks != null)
        {
            for (int var2 = this.beforeGetBrightnessHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeGetBrightnessHooks[var2].beforeGetBrightness(var1);
            }
        }

        float var4;

        if (this.overrideGetBrightnessHooks != null)
        {
            var4 = this.overrideGetBrightnessHooks[this.overrideGetBrightnessHooks.length - 1].getBrightness(var1);
        }
        else
        {
            var4 = this.player.localGetBrightness(var1);
        }

        if (this.afterGetBrightnessHooks != null)
        {
            for (int var3 = 0; var3 < this.afterGetBrightnessHooks.length; ++var3)
            {
                this.afterGetBrightnessHooks[var3].afterGetBrightness(var1);
            }
        }

        return var4;
    }

    protected PlayerBase GetOverwrittenGetBrightness(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetBrightnessHooks.length; ++var2)
        {
            if (this.overrideGetBrightnessHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetBrightnessHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static int getBrightnessForRender(EntityPlayerSP var0, float var1)
    {
        int var2;

        if (var0.playerAPI != null)
        {
            var2 = var0.playerAPI.getBrightnessForRender(var1);
        }
        else
        {
            var2 = var0.localGetBrightnessForRender(var1);
        }

        return var2;
    }

    private int getBrightnessForRender(float var1)
    {
        int var2;

        if (this.beforeGetBrightnessForRenderHooks != null)
        {
            for (var2 = this.beforeGetBrightnessForRenderHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeGetBrightnessForRenderHooks[var2].beforeGetBrightnessForRender(var1);
            }
        }

        if (this.overrideGetBrightnessForRenderHooks != null)
        {
            var2 = this.overrideGetBrightnessForRenderHooks[this.overrideGetBrightnessForRenderHooks.length - 1].getBrightnessForRender(var1);
        }
        else
        {
            var2 = this.player.localGetBrightnessForRender(var1);
        }

        if (this.afterGetBrightnessForRenderHooks != null)
        {
            for (int var3 = 0; var3 < this.afterGetBrightnessForRenderHooks.length; ++var3)
            {
                this.afterGetBrightnessForRenderHooks[var3].afterGetBrightnessForRender(var1);
            }
        }

        return var2;
    }

    protected PlayerBase GetOverwrittenGetBrightnessForRender(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetBrightnessForRenderHooks.length; ++var2)
        {
            if (this.overrideGetBrightnessForRenderHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetBrightnessForRenderHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static float getCurrentPlayerStrVsBlock(EntityPlayerSP var0, Block var1)
    {
        float var2;

        if (var0.playerAPI != null)
        {
            var2 = var0.playerAPI.getCurrentPlayerStrVsBlock(var1);
        }
        else
        {
            var2 = var0.localGetCurrentPlayerStrVsBlock(var1);
        }

        return var2;
    }

    private float getCurrentPlayerStrVsBlock(Block var1)
    {
        if (this.beforeGetCurrentPlayerStrVsBlockHooks != null)
        {
            for (int var2 = this.beforeGetCurrentPlayerStrVsBlockHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeGetCurrentPlayerStrVsBlockHooks[var2].beforeGetCurrentPlayerStrVsBlock(var1);
            }
        }

        float var4;

        if (this.overrideGetCurrentPlayerStrVsBlockHooks != null)
        {
            var4 = this.overrideGetCurrentPlayerStrVsBlockHooks[this.overrideGetCurrentPlayerStrVsBlockHooks.length - 1].getCurrentPlayerStrVsBlock(var1);
        }
        else
        {
            var4 = this.player.localGetCurrentPlayerStrVsBlock(var1);
        }

        if (this.afterGetCurrentPlayerStrVsBlockHooks != null)
        {
            for (int var3 = 0; var3 < this.afterGetCurrentPlayerStrVsBlockHooks.length; ++var3)
            {
                this.afterGetCurrentPlayerStrVsBlockHooks[var3].afterGetCurrentPlayerStrVsBlock(var1);
            }
        }

        return var4;
    }

    protected PlayerBase GetOverwrittenGetCurrentPlayerStrVsBlock(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetCurrentPlayerStrVsBlockHooks.length; ++var2)
        {
            if (this.overrideGetCurrentPlayerStrVsBlockHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetCurrentPlayerStrVsBlockHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static double getDistanceSq(EntityPlayerSP var0, double var1, double var3, double var5)
    {
        double var7;

        if (var0.playerAPI != null)
        {
            var7 = var0.playerAPI.getDistanceSq(var1, var3, var5);
        }
        else
        {
            var7 = var0.localGetDistanceSq(var1, var3, var5);
        }

        return var7;
    }

    private double getDistanceSq(double var1, double var3, double var5)
    {
        if (this.beforeGetDistanceSqHooks != null)
        {
            for (int var7 = this.beforeGetDistanceSqHooks.length - 1; var7 >= 0; --var7)
            {
                this.beforeGetDistanceSqHooks[var7].beforeGetDistanceSq(var1, var3, var5);
            }
        }

        double var10;

        if (this.overrideGetDistanceSqHooks != null)
        {
            var10 = this.overrideGetDistanceSqHooks[this.overrideGetDistanceSqHooks.length - 1].getDistanceSq(var1, var3, var5);
        }
        else
        {
            var10 = this.player.localGetDistanceSq(var1, var3, var5);
        }

        if (this.afterGetDistanceSqHooks != null)
        {
            for (int var9 = 0; var9 < this.afterGetDistanceSqHooks.length; ++var9)
            {
                this.afterGetDistanceSqHooks[var9].afterGetDistanceSq(var1, var3, var5);
            }
        }

        return var10;
    }

    protected PlayerBase GetOverwrittenGetDistanceSq(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetDistanceSqHooks.length; ++var2)
        {
            if (this.overrideGetDistanceSqHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetDistanceSqHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static double getDistanceSqToEntity(EntityPlayerSP var0, Entity var1)
    {
        double var2;

        if (var0.playerAPI != null)
        {
            var2 = var0.playerAPI.getDistanceSqToEntity(var1);
        }
        else
        {
            var2 = var0.localGetDistanceSqToEntity(var1);
        }

        return var2;
    }

    private double getDistanceSqToEntity(Entity var1)
    {
        if (this.beforeGetDistanceSqToEntityHooks != null)
        {
            for (int var2 = this.beforeGetDistanceSqToEntityHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeGetDistanceSqToEntityHooks[var2].beforeGetDistanceSqToEntity(var1);
            }
        }

        double var5;

        if (this.overrideGetDistanceSqToEntityHooks != null)
        {
            var5 = this.overrideGetDistanceSqToEntityHooks[this.overrideGetDistanceSqToEntityHooks.length - 1].getDistanceSqToEntity(var1);
        }
        else
        {
            var5 = this.player.localGetDistanceSqToEntity(var1);
        }

        if (this.afterGetDistanceSqToEntityHooks != null)
        {
            for (int var4 = 0; var4 < this.afterGetDistanceSqToEntityHooks.length; ++var4)
            {
                this.afterGetDistanceSqToEntityHooks[var4].afterGetDistanceSqToEntity(var1);
            }
        }

        return var5;
    }

    protected PlayerBase GetOverwrittenGetDistanceSqToEntity(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetDistanceSqToEntityHooks.length; ++var2)
        {
            if (this.overrideGetDistanceSqToEntityHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetDistanceSqToEntityHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static float getFOVMultiplier(EntityPlayerSP var0)
    {
        float var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.getFOVMultiplier();
        }
        else
        {
            var1 = var0.localGetFOVMultiplier();
        }

        return var1;
    }

    private float getFOVMultiplier()
    {
        if (this.beforeGetFOVMultiplierHooks != null)
        {
            for (int var1 = this.beforeGetFOVMultiplierHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeGetFOVMultiplierHooks[var1].beforeGetFOVMultiplier();
            }
        }

        float var3;

        if (this.overrideGetFOVMultiplierHooks != null)
        {
            var3 = this.overrideGetFOVMultiplierHooks[this.overrideGetFOVMultiplierHooks.length - 1].getFOVMultiplier();
        }
        else
        {
            var3 = this.player.localGetFOVMultiplier();
        }

        if (this.afterGetFOVMultiplierHooks != null)
        {
            for (int var2 = 0; var2 < this.afterGetFOVMultiplierHooks.length; ++var2)
            {
                this.afterGetFOVMultiplierHooks[var2].afterGetFOVMultiplier();
            }
        }

        return var3;
    }

    protected PlayerBase GetOverwrittenGetFOVMultiplier(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetFOVMultiplierHooks.length; ++var2)
        {
            if (this.overrideGetFOVMultiplierHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetFOVMultiplierHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static String getHurtSound(EntityPlayerSP var0)
    {
        String var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.getHurtSound();
        }
        else
        {
            var1 = var0.localGetHurtSound();
        }

        return var1;
    }

    private String getHurtSound()
    {
        if (this.beforeGetHurtSoundHooks != null)
        {
            for (int var1 = this.beforeGetHurtSoundHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeGetHurtSoundHooks[var1].beforeGetHurtSound();
            }
        }

        String var3;

        if (this.overrideGetHurtSoundHooks != null)
        {
            var3 = this.overrideGetHurtSoundHooks[this.overrideGetHurtSoundHooks.length - 1].getHurtSound();
        }
        else
        {
            var3 = this.player.localGetHurtSound();
        }

        if (this.afterGetHurtSoundHooks != null)
        {
            for (int var2 = 0; var2 < this.afterGetHurtSoundHooks.length; ++var2)
            {
                this.afterGetHurtSoundHooks[var2].afterGetHurtSound();
            }
        }

        return var3;
    }

    protected PlayerBase GetOverwrittenGetHurtSound(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetHurtSoundHooks.length; ++var2)
        {
            if (this.overrideGetHurtSoundHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetHurtSoundHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static int getItemIcon(EntityPlayerSP var0, ItemStack var1, int var2)
    {
        int var3;

        if (var0.playerAPI != null)
        {
            var3 = var0.playerAPI.getItemIcon(var1, var2);
        }
        else
        {
            var3 = var0.localGetItemIcon(var1, var2);
        }

        return var3;
    }

    private int getItemIcon(ItemStack var1, int var2)
    {
        int var3;

        if (this.beforeGetItemIconHooks != null)
        {
            for (var3 = this.beforeGetItemIconHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeGetItemIconHooks[var3].beforeGetItemIcon(var1, var2);
            }
        }

        if (this.overrideGetItemIconHooks != null)
        {
            var3 = this.overrideGetItemIconHooks[this.overrideGetItemIconHooks.length - 1].getItemIcon(var1, var2);
        }
        else
        {
            var3 = this.player.localGetItemIcon(var1, var2);
        }

        if (this.afterGetItemIconHooks != null)
        {
            for (int var4 = 0; var4 < this.afterGetItemIconHooks.length; ++var4)
            {
                this.afterGetItemIconHooks[var4].afterGetItemIcon(var1, var2);
            }
        }

        return var3;
    }

    protected PlayerBase GetOverwrittenGetItemIcon(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetItemIconHooks.length; ++var2)
        {
            if (this.overrideGetItemIconHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetItemIconHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static int getMaxHealth(EntityPlayerSP var0)
    {
        int var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.getMaxHealth();
        }
        else
        {
            var1 = var0.localGetMaxHealth();
        }

        return var1;
    }

    private int getMaxHealth()
    {
        int var1;

        if (this.beforeGetMaxHealthHooks != null)
        {
            for (var1 = this.beforeGetMaxHealthHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeGetMaxHealthHooks[var1].beforeGetMaxHealth();
            }
        }

        if (this.overrideGetMaxHealthHooks != null)
        {
            var1 = this.overrideGetMaxHealthHooks[this.overrideGetMaxHealthHooks.length - 1].getMaxHealth();
        }
        else
        {
            var1 = this.player.localGetMaxHealth();
        }

        if (this.afterGetMaxHealthHooks != null)
        {
            for (int var2 = 0; var2 < this.afterGetMaxHealthHooks.length; ++var2)
            {
                this.afterGetMaxHealthHooks[var2].afterGetMaxHealth();
            }
        }

        return var1;
    }

    protected PlayerBase GetOverwrittenGetMaxHealth(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetMaxHealthHooks.length; ++var2)
        {
            if (this.overrideGetMaxHealthHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetMaxHealthHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static int getSleepTimer(EntityPlayerSP var0)
    {
        int var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.getSleepTimer();
        }
        else
        {
            var1 = var0.localGetSleepTimer();
        }

        return var1;
    }

    private int getSleepTimer()
    {
        int var1;

        if (this.beforeGetSleepTimerHooks != null)
        {
            for (var1 = this.beforeGetSleepTimerHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeGetSleepTimerHooks[var1].beforeGetSleepTimer();
            }
        }

        if (this.overrideGetSleepTimerHooks != null)
        {
            var1 = this.overrideGetSleepTimerHooks[this.overrideGetSleepTimerHooks.length - 1].getSleepTimer();
        }
        else
        {
            var1 = this.player.localGetSleepTimer();
        }

        if (this.afterGetSleepTimerHooks != null)
        {
            for (int var2 = 0; var2 < this.afterGetSleepTimerHooks.length; ++var2)
            {
                this.afterGetSleepTimerHooks[var2].afterGetSleepTimer();
            }
        }

        return var1;
    }

    protected PlayerBase GetOverwrittenGetSleepTimer(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetSleepTimerHooks.length; ++var2)
        {
            if (this.overrideGetSleepTimerHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetSleepTimerHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static float getSpeedModifier(EntityPlayerSP var0)
    {
        float var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.getSpeedModifier();
        }
        else
        {
            var1 = var0.localGetSpeedModifier();
        }

        return var1;
    }

    private float getSpeedModifier()
    {
        if (this.beforeGetSpeedModifierHooks != null)
        {
            for (int var1 = this.beforeGetSpeedModifierHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeGetSpeedModifierHooks[var1].beforeGetSpeedModifier();
            }
        }

        float var3;

        if (this.overrideGetSpeedModifierHooks != null)
        {
            var3 = this.overrideGetSpeedModifierHooks[this.overrideGetSpeedModifierHooks.length - 1].getSpeedModifier();
        }
        else
        {
            var3 = this.player.localGetSpeedModifier();
        }

        if (this.afterGetSpeedModifierHooks != null)
        {
            for (int var2 = 0; var2 < this.afterGetSpeedModifierHooks.length; ++var2)
            {
                this.afterGetSpeedModifierHooks[var2].afterGetSpeedModifier();
            }
        }

        return var3;
    }

    protected PlayerBase GetOverwrittenGetSpeedModifier(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetSpeedModifierHooks.length; ++var2)
        {
            if (this.overrideGetSpeedModifierHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetSpeedModifierHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean handleLavaMovement(EntityPlayerSP var0)
    {
        boolean var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.handleLavaMovement();
        }
        else
        {
            var1 = var0.localHandleLavaMovement();
        }

        return var1;
    }

    private boolean handleLavaMovement()
    {
        if (this.beforeHandleLavaMovementHooks != null)
        {
            for (int var1 = this.beforeHandleLavaMovementHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeHandleLavaMovementHooks[var1].beforeHandleLavaMovement();
            }
        }

        boolean var3;

        if (this.overrideHandleLavaMovementHooks != null)
        {
            var3 = this.overrideHandleLavaMovementHooks[this.overrideHandleLavaMovementHooks.length - 1].handleLavaMovement();
        }
        else
        {
            var3 = this.player.localHandleLavaMovement();
        }

        if (this.afterHandleLavaMovementHooks != null)
        {
            for (int var2 = 0; var2 < this.afterHandleLavaMovementHooks.length; ++var2)
            {
                this.afterHandleLavaMovementHooks[var2].afterHandleLavaMovement();
            }
        }

        return var3;
    }

    protected PlayerBase GetOverwrittenHandleLavaMovement(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideHandleLavaMovementHooks.length; ++var2)
        {
            if (this.overrideHandleLavaMovementHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideHandleLavaMovementHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean handleWaterMovement(EntityPlayerSP var0)
    {
        boolean var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.handleWaterMovement();
        }
        else
        {
            var1 = var0.localHandleWaterMovement();
        }

        return var1;
    }

    private boolean handleWaterMovement()
    {
        if (this.beforeHandleWaterMovementHooks != null)
        {
            for (int var1 = this.beforeHandleWaterMovementHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeHandleWaterMovementHooks[var1].beforeHandleWaterMovement();
            }
        }

        boolean var3;

        if (this.overrideHandleWaterMovementHooks != null)
        {
            var3 = this.overrideHandleWaterMovementHooks[this.overrideHandleWaterMovementHooks.length - 1].handleWaterMovement();
        }
        else
        {
            var3 = this.player.localHandleWaterMovement();
        }

        if (this.afterHandleWaterMovementHooks != null)
        {
            for (int var2 = 0; var2 < this.afterHandleWaterMovementHooks.length; ++var2)
            {
                this.afterHandleWaterMovementHooks[var2].afterHandleWaterMovement();
            }
        }

        return var3;
    }

    protected PlayerBase GetOverwrittenHandleWaterMovement(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideHandleWaterMovementHooks.length; ++var2)
        {
            if (this.overrideHandleWaterMovementHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideHandleWaterMovementHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void heal(EntityPlayerSP var0, int var1)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.heal(var1);
        }
        else
        {
            var0.localHeal(var1);
        }
    }

    private void heal(int var1)
    {
        int var2;

        if (this.beforeHealHooks != null)
        {
            for (var2 = this.beforeHealHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeHealHooks[var2].beforeHeal(var1);
            }
        }

        if (this.overrideHealHooks != null)
        {
            this.overrideHealHooks[this.overrideHealHooks.length - 1].heal(var1);
        }
        else
        {
            this.player.localHeal(var1);
        }

        if (this.afterHealHooks != null)
        {
            for (var2 = 0; var2 < this.afterHealHooks.length; ++var2)
            {
                this.afterHealHooks[var2].afterHeal(var1);
            }
        }
    }

    protected PlayerBase GetOverwrittenHeal(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideHealHooks.length; ++var2)
        {
            if (this.overrideHealHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideHealHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean interact(EntityPlayerSP var0, EntityPlayer var1)
    {
        boolean var2;

        if (var0.playerAPI != null)
        {
            var2 = var0.playerAPI.interact(var1);
        }
        else
        {
            var2 = var0.localInteract(var1);
        }

        return var2;
    }

    private boolean interact(EntityPlayer var1)
    {
        if (this.beforeInteractHooks != null)
        {
            for (int var2 = this.beforeInteractHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeInteractHooks[var2].beforeInteract(var1);
            }
        }

        boolean var4;

        if (this.overrideInteractHooks != null)
        {
            var4 = this.overrideInteractHooks[this.overrideInteractHooks.length - 1].interact(var1);
        }
        else
        {
            var4 = this.player.localInteract(var1);
        }

        if (this.afterInteractHooks != null)
        {
            for (int var3 = 0; var3 < this.afterInteractHooks.length; ++var3)
            {
                this.afterInteractHooks[var3].afterInteract(var1);
            }
        }

        return var4;
    }

    protected PlayerBase GetOverwrittenInteract(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideInteractHooks.length; ++var2)
        {
            if (this.overrideInteractHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideInteractHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean isEntityInsideOpaqueBlock(EntityPlayerSP var0)
    {
        boolean var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.isEntityInsideOpaqueBlock();
        }
        else
        {
            var1 = var0.localIsEntityInsideOpaqueBlock();
        }

        return var1;
    }

    private boolean isEntityInsideOpaqueBlock()
    {
        if (this.beforeIsEntityInsideOpaqueBlockHooks != null)
        {
            for (int var1 = this.beforeIsEntityInsideOpaqueBlockHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeIsEntityInsideOpaqueBlockHooks[var1].beforeIsEntityInsideOpaqueBlock();
            }
        }

        boolean var3;

        if (this.overrideIsEntityInsideOpaqueBlockHooks != null)
        {
            var3 = this.overrideIsEntityInsideOpaqueBlockHooks[this.overrideIsEntityInsideOpaqueBlockHooks.length - 1].isEntityInsideOpaqueBlock();
        }
        else
        {
            var3 = this.player.localIsEntityInsideOpaqueBlock();
        }

        if (this.afterIsEntityInsideOpaqueBlockHooks != null)
        {
            for (int var2 = 0; var2 < this.afterIsEntityInsideOpaqueBlockHooks.length; ++var2)
            {
                this.afterIsEntityInsideOpaqueBlockHooks[var2].afterIsEntityInsideOpaqueBlock();
            }
        }

        return var3;
    }

    protected PlayerBase GetOverwrittenIsEntityInsideOpaqueBlock(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideIsEntityInsideOpaqueBlockHooks.length; ++var2)
        {
            if (this.overrideIsEntityInsideOpaqueBlockHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideIsEntityInsideOpaqueBlockHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean isInWater(EntityPlayerSP var0)
    {
        boolean var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.isInWater();
        }
        else
        {
            var1 = var0.localIsInWater();
        }

        return var1;
    }

    private boolean isInWater()
    {
        if (this.beforeIsInWaterHooks != null)
        {
            for (int var1 = this.beforeIsInWaterHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeIsInWaterHooks[var1].beforeIsInWater();
            }
        }

        boolean var3;

        if (this.overrideIsInWaterHooks != null)
        {
            var3 = this.overrideIsInWaterHooks[this.overrideIsInWaterHooks.length - 1].isInWater();
        }
        else
        {
            var3 = this.player.localIsInWater();
        }

        if (this.afterIsInWaterHooks != null)
        {
            for (int var2 = 0; var2 < this.afterIsInWaterHooks.length; ++var2)
            {
                this.afterIsInWaterHooks[var2].afterIsInWater();
            }
        }

        return var3;
    }

    protected PlayerBase GetOverwrittenIsInWater(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideIsInWaterHooks.length; ++var2)
        {
            if (this.overrideIsInWaterHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideIsInWaterHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean isInsideOfMaterial(EntityPlayerSP var0, Material var1)
    {
        boolean var2;

        if (var0.playerAPI != null)
        {
            var2 = var0.playerAPI.isInsideOfMaterial(var1);
        }
        else
        {
            var2 = var0.localIsInsideOfMaterial(var1);
        }

        return var2;
    }

    private boolean isInsideOfMaterial(Material var1)
    {
        if (this.beforeIsInsideOfMaterialHooks != null)
        {
            for (int var2 = this.beforeIsInsideOfMaterialHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeIsInsideOfMaterialHooks[var2].beforeIsInsideOfMaterial(var1);
            }
        }

        boolean var4;

        if (this.overrideIsInsideOfMaterialHooks != null)
        {
            var4 = this.overrideIsInsideOfMaterialHooks[this.overrideIsInsideOfMaterialHooks.length - 1].isInsideOfMaterial(var1);
        }
        else
        {
            var4 = this.player.localIsInsideOfMaterial(var1);
        }

        if (this.afterIsInsideOfMaterialHooks != null)
        {
            for (int var3 = 0; var3 < this.afterIsInsideOfMaterialHooks.length; ++var3)
            {
                this.afterIsInsideOfMaterialHooks[var3].afterIsInsideOfMaterial(var1);
            }
        }

        return var4;
    }

    protected PlayerBase GetOverwrittenIsInsideOfMaterial(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideIsInsideOfMaterialHooks.length; ++var2)
        {
            if (this.overrideIsInsideOfMaterialHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideIsInsideOfMaterialHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean isOnLadder(EntityPlayerSP var0)
    {
        boolean var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.isOnLadder();
        }
        else
        {
            var1 = var0.localIsOnLadder();
        }

        return var1;
    }

    private boolean isOnLadder()
    {
        if (this.beforeIsOnLadderHooks != null)
        {
            for (int var1 = this.beforeIsOnLadderHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeIsOnLadderHooks[var1].beforeIsOnLadder();
            }
        }

        boolean var3;

        if (this.overrideIsOnLadderHooks != null)
        {
            var3 = this.overrideIsOnLadderHooks[this.overrideIsOnLadderHooks.length - 1].isOnLadder();
        }
        else
        {
            var3 = this.player.localIsOnLadder();
        }

        if (this.afterIsOnLadderHooks != null)
        {
            for (int var2 = 0; var2 < this.afterIsOnLadderHooks.length; ++var2)
            {
                this.afterIsOnLadderHooks[var2].afterIsOnLadder();
            }
        }

        return var3;
    }

    protected PlayerBase GetOverwrittenIsOnLadder(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideIsOnLadderHooks.length; ++var2)
        {
            if (this.overrideIsOnLadderHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideIsOnLadderHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean isPlayerSleeping(EntityPlayerSP var0)
    {
        boolean var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.isPlayerSleeping();
        }
        else
        {
            var1 = var0.localIsPlayerSleeping();
        }

        return var1;
    }

    private boolean isPlayerSleeping()
    {
        if (this.beforeIsPlayerSleepingHooks != null)
        {
            for (int var1 = this.beforeIsPlayerSleepingHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeIsPlayerSleepingHooks[var1].beforeIsPlayerSleeping();
            }
        }

        boolean var3;

        if (this.overrideIsPlayerSleepingHooks != null)
        {
            var3 = this.overrideIsPlayerSleepingHooks[this.overrideIsPlayerSleepingHooks.length - 1].isPlayerSleeping();
        }
        else
        {
            var3 = this.player.localIsPlayerSleeping();
        }

        if (this.afterIsPlayerSleepingHooks != null)
        {
            for (int var2 = 0; var2 < this.afterIsPlayerSleepingHooks.length; ++var2)
            {
                this.afterIsPlayerSleepingHooks[var2].afterIsPlayerSleeping();
            }
        }

        return var3;
    }

    protected PlayerBase GetOverwrittenIsPlayerSleeping(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideIsPlayerSleepingHooks.length; ++var2)
        {
            if (this.overrideIsPlayerSleepingHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideIsPlayerSleepingHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean isSneaking(EntityPlayerSP var0)
    {
        boolean var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.isSneaking();
        }
        else
        {
            var1 = var0.localIsSneaking();
        }

        return var1;
    }

    private boolean isSneaking()
    {
        if (this.beforeIsSneakingHooks != null)
        {
            for (int var1 = this.beforeIsSneakingHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeIsSneakingHooks[var1].beforeIsSneaking();
            }
        }

        boolean var3;

        if (this.overrideIsSneakingHooks != null)
        {
            var3 = this.overrideIsSneakingHooks[this.overrideIsSneakingHooks.length - 1].isSneaking();
        }
        else
        {
            var3 = this.player.localIsSneaking();
        }

        if (this.afterIsSneakingHooks != null)
        {
            for (int var2 = 0; var2 < this.afterIsSneakingHooks.length; ++var2)
            {
                this.afterIsSneakingHooks[var2].afterIsSneaking();
            }
        }

        return var3;
    }

    protected PlayerBase GetOverwrittenIsSneaking(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideIsSneakingHooks.length; ++var2)
        {
            if (this.overrideIsSneakingHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideIsSneakingHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean isSprinting(EntityPlayerSP var0)
    {
        boolean var1;

        if (var0.playerAPI != null)
        {
            var1 = var0.playerAPI.isSprinting();
        }
        else
        {
            var1 = var0.localIsSprinting();
        }

        return var1;
    }

    private boolean isSprinting()
    {
        if (this.beforeIsSprintingHooks != null)
        {
            for (int var1 = this.beforeIsSprintingHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeIsSprintingHooks[var1].beforeIsSprinting();
            }
        }

        boolean var3;

        if (this.overrideIsSprintingHooks != null)
        {
            var3 = this.overrideIsSprintingHooks[this.overrideIsSprintingHooks.length - 1].isSprinting();
        }
        else
        {
            var3 = this.player.localIsSprinting();
        }

        if (this.afterIsSprintingHooks != null)
        {
            for (int var2 = 0; var2 < this.afterIsSprintingHooks.length; ++var2)
            {
                this.afterIsSprintingHooks[var2].afterIsSprinting();
            }
        }

        return var3;
    }

    protected PlayerBase GetOverwrittenIsSprinting(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideIsSprintingHooks.length; ++var2)
        {
            if (this.overrideIsSprintingHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideIsSprintingHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void jump(EntityPlayerSP var0)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.jump();
        }
        else
        {
            var0.localJump();
        }
    }

    private void jump()
    {
        int var1;

        if (this.beforeJumpHooks != null)
        {
            for (var1 = this.beforeJumpHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeJumpHooks[var1].beforeJump();
            }
        }

        if (this.overrideJumpHooks != null)
        {
            this.overrideJumpHooks[this.overrideJumpHooks.length - 1].jump();
        }
        else
        {
            this.player.localJump();
        }

        if (this.afterJumpHooks != null)
        {
            for (var1 = 0; var1 < this.afterJumpHooks.length; ++var1)
            {
                this.afterJumpHooks[var1].afterJump();
            }
        }
    }

    protected PlayerBase GetOverwrittenJump(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideJumpHooks.length; ++var2)
        {
            if (this.overrideJumpHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideJumpHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void knockBack(EntityPlayerSP var0, Entity var1, int var2, double var3, double var5)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.knockBack(var1, var2, var3, var5);
        }
        else
        {
            var0.localKnockBack(var1, var2, var3, var5);
        }
    }

    private void knockBack(Entity var1, int var2, double var3, double var5)
    {
        int var7;

        if (this.beforeKnockBackHooks != null)
        {
            for (var7 = this.beforeKnockBackHooks.length - 1; var7 >= 0; --var7)
            {
                this.beforeKnockBackHooks[var7].beforeKnockBack(var1, var2, var3, var5);
            }
        }

        if (this.overrideKnockBackHooks != null)
        {
            this.overrideKnockBackHooks[this.overrideKnockBackHooks.length - 1].knockBack(var1, var2, var3, var5);
        }
        else
        {
            this.player.localKnockBack(var1, var2, var3, var5);
        }

        if (this.afterKnockBackHooks != null)
        {
            for (var7 = 0; var7 < this.afterKnockBackHooks.length; ++var7)
            {
                this.afterKnockBackHooks[var7].afterKnockBack(var1, var2, var3, var5);
            }
        }
    }

    protected PlayerBase GetOverwrittenKnockBack(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideKnockBackHooks.length; ++var2)
        {
            if (this.overrideKnockBackHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideKnockBackHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void moveEntity(EntityPlayerSP var0, double var1, double var3, double var5)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.moveEntity(var1, var3, var5);
        }
        else
        {
            var0.localMoveEntity(var1, var3, var5);
        }
    }

    private void moveEntity(double var1, double var3, double var5)
    {
        int var7;

        if (this.beforeMoveEntityHooks != null)
        {
            for (var7 = this.beforeMoveEntityHooks.length - 1; var7 >= 0; --var7)
            {
                this.beforeMoveEntityHooks[var7].beforeMoveEntity(var1, var3, var5);
            }
        }

        if (this.overrideMoveEntityHooks != null)
        {
            this.overrideMoveEntityHooks[this.overrideMoveEntityHooks.length - 1].moveEntity(var1, var3, var5);
        }
        else
        {
            this.player.localMoveEntity(var1, var3, var5);
        }

        if (this.afterMoveEntityHooks != null)
        {
            for (var7 = 0; var7 < this.afterMoveEntityHooks.length; ++var7)
            {
                this.afterMoveEntityHooks[var7].afterMoveEntity(var1, var3, var5);
            }
        }
    }

    protected PlayerBase GetOverwrittenMoveEntity(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideMoveEntityHooks.length; ++var2)
        {
            if (this.overrideMoveEntityHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideMoveEntityHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void moveEntityWithHeading(EntityPlayerSP var0, float var1, float var2)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.moveEntityWithHeading(var1, var2);
        }
        else
        {
            var0.localMoveEntityWithHeading(var1, var2);
        }
    }

    private void moveEntityWithHeading(float var1, float var2)
    {
        int var3;

        if (this.beforeMoveEntityWithHeadingHooks != null)
        {
            for (var3 = this.beforeMoveEntityWithHeadingHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeMoveEntityWithHeadingHooks[var3].beforeMoveEntityWithHeading(var1, var2);
            }
        }

        if (this.overrideMoveEntityWithHeadingHooks != null)
        {
            this.overrideMoveEntityWithHeadingHooks[this.overrideMoveEntityWithHeadingHooks.length - 1].moveEntityWithHeading(var1, var2);
        }
        else
        {
            this.player.localMoveEntityWithHeading(var1, var2);
        }

        if (this.afterMoveEntityWithHeadingHooks != null)
        {
            for (var3 = 0; var3 < this.afterMoveEntityWithHeadingHooks.length; ++var3)
            {
                this.afterMoveEntityWithHeadingHooks[var3].afterMoveEntityWithHeading(var1, var2);
            }
        }
    }

    protected PlayerBase GetOverwrittenMoveEntityWithHeading(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideMoveEntityWithHeadingHooks.length; ++var2)
        {
            if (this.overrideMoveEntityWithHeadingHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideMoveEntityWithHeadingHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void moveFlying(EntityPlayerSP var0, float var1, float var2, float var3)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.moveFlying(var1, var2, var3);
        }
        else
        {
            var0.localMoveFlying(var1, var2, var3);
        }
    }

    private void moveFlying(float var1, float var2, float var3)
    {
        int var4;

        if (this.beforeMoveFlyingHooks != null)
        {
            for (var4 = this.beforeMoveFlyingHooks.length - 1; var4 >= 0; --var4)
            {
                this.beforeMoveFlyingHooks[var4].beforeMoveFlying(var1, var2, var3);
            }
        }

        if (this.overrideMoveFlyingHooks != null)
        {
            this.overrideMoveFlyingHooks[this.overrideMoveFlyingHooks.length - 1].moveFlying(var1, var2, var3);
        }
        else
        {
            this.player.localMoveFlying(var1, var2, var3);
        }

        if (this.afterMoveFlyingHooks != null)
        {
            for (var4 = 0; var4 < this.afterMoveFlyingHooks.length; ++var4)
            {
                this.afterMoveFlyingHooks[var4].afterMoveFlying(var1, var2, var3);
            }
        }
    }

    protected PlayerBase GetOverwrittenMoveFlying(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideMoveFlyingHooks.length; ++var2)
        {
            if (this.overrideMoveFlyingHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideMoveFlyingHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void onDeath(EntityPlayerSP var0, DamageSource var1)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.onDeath(var1);
        }
        else
        {
            var0.localOnDeath(var1);
        }
    }

    private void onDeath(DamageSource var1)
    {
        int var2;

        if (this.beforeOnDeathHooks != null)
        {
            for (var2 = this.beforeOnDeathHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeOnDeathHooks[var2].beforeOnDeath(var1);
            }
        }

        if (this.overrideOnDeathHooks != null)
        {
            this.overrideOnDeathHooks[this.overrideOnDeathHooks.length - 1].onDeath(var1);
        }
        else
        {
            this.player.localOnDeath(var1);
        }

        if (this.afterOnDeathHooks != null)
        {
            for (var2 = 0; var2 < this.afterOnDeathHooks.length; ++var2)
            {
                this.afterOnDeathHooks[var2].afterOnDeath(var1);
            }
        }
    }

    protected PlayerBase GetOverwrittenOnDeath(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideOnDeathHooks.length; ++var2)
        {
            if (this.overrideOnDeathHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideOnDeathHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void onLivingUpdate(EntityPlayerSP var0)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.onLivingUpdate();
        }
        else
        {
            var0.localOnLivingUpdate();
        }
    }

    private void onLivingUpdate()
    {
        int var1;

        if (this.beforeOnLivingUpdateHooks != null)
        {
            for (var1 = this.beforeOnLivingUpdateHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeOnLivingUpdateHooks[var1].beforeOnLivingUpdate();
            }
        }

        if (this.overrideOnLivingUpdateHooks != null)
        {
            this.overrideOnLivingUpdateHooks[this.overrideOnLivingUpdateHooks.length - 1].onLivingUpdate();
        }
        else
        {
            this.player.localOnLivingUpdate();
        }

        if (this.afterOnLivingUpdateHooks != null)
        {
            for (var1 = 0; var1 < this.afterOnLivingUpdateHooks.length; ++var1)
            {
                this.afterOnLivingUpdateHooks[var1].afterOnLivingUpdate();
            }
        }
    }

    protected PlayerBase GetOverwrittenOnLivingUpdate(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideOnLivingUpdateHooks.length; ++var2)
        {
            if (this.overrideOnLivingUpdateHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideOnLivingUpdateHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void onKillEntity(EntityPlayerSP var0, EntityLiving var1)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.onKillEntity(var1);
        }
        else
        {
            var0.localOnKillEntity(var1);
        }
    }

    private void onKillEntity(EntityLiving var1)
    {
        int var2;

        if (this.beforeOnKillEntityHooks != null)
        {
            for (var2 = this.beforeOnKillEntityHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeOnKillEntityHooks[var2].beforeOnKillEntity(var1);
            }
        }

        if (this.overrideOnKillEntityHooks != null)
        {
            this.overrideOnKillEntityHooks[this.overrideOnKillEntityHooks.length - 1].onKillEntity(var1);
        }
        else
        {
            this.player.localOnKillEntity(var1);
        }

        if (this.afterOnKillEntityHooks != null)
        {
            for (var2 = 0; var2 < this.afterOnKillEntityHooks.length; ++var2)
            {
                this.afterOnKillEntityHooks[var2].afterOnKillEntity(var1);
            }
        }
    }

    protected PlayerBase GetOverwrittenOnKillEntity(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideOnKillEntityHooks.length; ++var2)
        {
            if (this.overrideOnKillEntityHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideOnKillEntityHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void onStruckByLightning(EntityPlayerSP var0, EntityLightningBolt var1)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.onStruckByLightning(var1);
        }
        else
        {
            var0.localOnStruckByLightning(var1);
        }
    }

    private void onStruckByLightning(EntityLightningBolt var1)
    {
        int var2;

        if (this.beforeOnStruckByLightningHooks != null)
        {
            for (var2 = this.beforeOnStruckByLightningHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeOnStruckByLightningHooks[var2].beforeOnStruckByLightning(var1);
            }
        }

        if (this.overrideOnStruckByLightningHooks != null)
        {
            this.overrideOnStruckByLightningHooks[this.overrideOnStruckByLightningHooks.length - 1].onStruckByLightning(var1);
        }
        else
        {
            this.player.localOnStruckByLightning(var1);
        }

        if (this.afterOnStruckByLightningHooks != null)
        {
            for (var2 = 0; var2 < this.afterOnStruckByLightningHooks.length; ++var2)
            {
                this.afterOnStruckByLightningHooks[var2].afterOnStruckByLightning(var1);
            }
        }
    }

    protected PlayerBase GetOverwrittenOnStruckByLightning(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideOnStruckByLightningHooks.length; ++var2)
        {
            if (this.overrideOnStruckByLightningHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideOnStruckByLightningHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void onUpdate(EntityPlayerSP var0)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.onUpdate();
        }
        else
        {
            var0.localOnUpdate();
        }
    }

    private void onUpdate()
    {
        int var1;

        if (this.beforeOnUpdateHooks != null)
        {
            for (var1 = this.beforeOnUpdateHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeOnUpdateHooks[var1].beforeOnUpdate();
            }
        }

        if (this.overrideOnUpdateHooks != null)
        {
            this.overrideOnUpdateHooks[this.overrideOnUpdateHooks.length - 1].onUpdate();
        }
        else
        {
            this.player.localOnUpdate();
        }

        if (this.afterOnUpdateHooks != null)
        {
            for (var1 = 0; var1 < this.afterOnUpdateHooks.length; ++var1)
            {
                this.afterOnUpdateHooks[var1].afterOnUpdate();
            }
        }
    }

    protected PlayerBase GetOverwrittenOnUpdate(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideOnUpdateHooks.length; ++var2)
        {
            if (this.overrideOnUpdateHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideOnUpdateHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void playStepSound(EntityPlayerSP var0, int var1, int var2, int var3, int var4)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.playStepSound(var1, var2, var3, var4);
        }
        else
        {
            var0.localPlayStepSound(var1, var2, var3, var4);
        }
    }

    private void playStepSound(int var1, int var2, int var3, int var4)
    {
        int var5;

        if (this.beforePlayStepSoundHooks != null)
        {
            for (var5 = this.beforePlayStepSoundHooks.length - 1; var5 >= 0; --var5)
            {
                this.beforePlayStepSoundHooks[var5].beforePlayStepSound(var1, var2, var3, var4);
            }
        }

        if (this.overridePlayStepSoundHooks != null)
        {
            this.overridePlayStepSoundHooks[this.overridePlayStepSoundHooks.length - 1].playStepSound(var1, var2, var3, var4);
        }
        else
        {
            this.player.localPlayStepSound(var1, var2, var3, var4);
        }

        if (this.afterPlayStepSoundHooks != null)
        {
            for (var5 = 0; var5 < this.afterPlayStepSoundHooks.length; ++var5)
            {
                this.afterPlayStepSoundHooks[var5].afterPlayStepSound(var1, var2, var3, var4);
            }
        }
    }

    protected PlayerBase GetOverwrittenPlayStepSound(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overridePlayStepSoundHooks.length; ++var2)
        {
            if (this.overridePlayStepSoundHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overridePlayStepSoundHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean pushOutOfBlocks(EntityPlayerSP var0, double var1, double var3, double var5)
    {
        boolean var7;

        if (var0.playerAPI != null)
        {
            var7 = var0.playerAPI.pushOutOfBlocks(var1, var3, var5);
        }
        else
        {
            var7 = var0.localPushOutOfBlocks(var1, var3, var5);
        }

        return var7;
    }

    private boolean pushOutOfBlocks(double var1, double var3, double var5)
    {
        if (this.beforePushOutOfBlocksHooks != null)
        {
            for (int var7 = this.beforePushOutOfBlocksHooks.length - 1; var7 >= 0; --var7)
            {
                this.beforePushOutOfBlocksHooks[var7].beforePushOutOfBlocks(var1, var3, var5);
            }
        }

        boolean var9;

        if (this.overridePushOutOfBlocksHooks != null)
        {
            var9 = this.overridePushOutOfBlocksHooks[this.overridePushOutOfBlocksHooks.length - 1].pushOutOfBlocks(var1, var3, var5);
        }
        else
        {
            var9 = this.player.localPushOutOfBlocks(var1, var3, var5);
        }

        if (this.afterPushOutOfBlocksHooks != null)
        {
            for (int var8 = 0; var8 < this.afterPushOutOfBlocksHooks.length; ++var8)
            {
                this.afterPushOutOfBlocksHooks[var8].afterPushOutOfBlocks(var1, var3, var5);
            }
        }

        return var9;
    }

    protected PlayerBase GetOverwrittenPushOutOfBlocks(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overridePushOutOfBlocksHooks.length; ++var2)
        {
            if (this.overridePushOutOfBlocksHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overridePushOutOfBlocksHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static MovingObjectPosition rayTrace(EntityPlayerSP var0, double var1, float var3)
    {
        MovingObjectPosition var4;

        if (var0.playerAPI != null)
        {
            var4 = var0.playerAPI.rayTrace(var1, var3);
        }
        else
        {
            var4 = var0.localRayTrace(var1, var3);
        }

        return var4;
    }

    private MovingObjectPosition rayTrace(double var1, float var3)
    {
        if (this.beforeRayTraceHooks != null)
        {
            for (int var4 = this.beforeRayTraceHooks.length - 1; var4 >= 0; --var4)
            {
                this.beforeRayTraceHooks[var4].beforeRayTrace(var1, var3);
            }
        }

        MovingObjectPosition var6;

        if (this.overrideRayTraceHooks != null)
        {
            var6 = this.overrideRayTraceHooks[this.overrideRayTraceHooks.length - 1].rayTrace(var1, var3);
        }
        else
        {
            var6 = this.player.localRayTrace(var1, var3);
        }

        if (this.afterRayTraceHooks != null)
        {
            for (int var5 = 0; var5 < this.afterRayTraceHooks.length; ++var5)
            {
                this.afterRayTraceHooks[var5].afterRayTrace(var1, var3);
            }
        }

        return var6;
    }

    protected PlayerBase GetOverwrittenRayTrace(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRayTraceHooks.length; ++var2)
        {
            if (this.overrideRayTraceHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRayTraceHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void readEntityFromNBT(EntityPlayerSP var0, NBTTagCompound var1)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.readEntityFromNBT(var1);
        }
        else
        {
            var0.localReadEntityFromNBT(var1);
        }
    }

    private void readEntityFromNBT(NBTTagCompound var1)
    {
        int var2;

        if (this.beforeReadEntityFromNBTHooks != null)
        {
            for (var2 = this.beforeReadEntityFromNBTHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeReadEntityFromNBTHooks[var2].beforeReadEntityFromNBT(var1);
            }
        }

        if (this.overrideReadEntityFromNBTHooks != null)
        {
            this.overrideReadEntityFromNBTHooks[this.overrideReadEntityFromNBTHooks.length - 1].readEntityFromNBT(var1);
        }
        else
        {
            this.player.localReadEntityFromNBT(var1);
        }

        if (this.afterReadEntityFromNBTHooks != null)
        {
            for (var2 = 0; var2 < this.afterReadEntityFromNBTHooks.length; ++var2)
            {
                this.afterReadEntityFromNBTHooks[var2].afterReadEntityFromNBT(var1);
            }
        }
    }

    protected PlayerBase GetOverwrittenReadEntityFromNBT(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideReadEntityFromNBTHooks.length; ++var2)
        {
            if (this.overrideReadEntityFromNBTHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideReadEntityFromNBTHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void respawnPlayer(EntityPlayerSP var0)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.respawnPlayer();
        }
        else
        {
            var0.localRespawnPlayer();
        }
    }

    private void respawnPlayer()
    {
        int var1;

        if (this.beforeRespawnPlayerHooks != null)
        {
            for (var1 = this.beforeRespawnPlayerHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeRespawnPlayerHooks[var1].beforeRespawnPlayer();
            }
        }

        if (this.overrideRespawnPlayerHooks != null)
        {
            this.overrideRespawnPlayerHooks[this.overrideRespawnPlayerHooks.length - 1].respawnPlayer();
        }
        else
        {
            this.player.localRespawnPlayer();
        }

        if (this.afterRespawnPlayerHooks != null)
        {
            for (var1 = 0; var1 < this.afterRespawnPlayerHooks.length; ++var1)
            {
                this.afterRespawnPlayerHooks[var1].afterRespawnPlayer();
            }
        }
    }

    protected PlayerBase GetOverwrittenRespawnPlayer(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRespawnPlayerHooks.length; ++var2)
        {
            if (this.overrideRespawnPlayerHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRespawnPlayerHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void setDead(EntityPlayerSP var0)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.setDead();
        }
        else
        {
            var0.localSetDead();
        }
    }

    private void setDead()
    {
        int var1;

        if (this.beforeSetDeadHooks != null)
        {
            for (var1 = this.beforeSetDeadHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeSetDeadHooks[var1].beforeSetDead();
            }
        }

        if (this.overrideSetDeadHooks != null)
        {
            this.overrideSetDeadHooks[this.overrideSetDeadHooks.length - 1].setDead();
        }
        else
        {
            this.player.localSetDead();
        }

        if (this.afterSetDeadHooks != null)
        {
            for (var1 = 0; var1 < this.afterSetDeadHooks.length; ++var1)
            {
                this.afterSetDeadHooks[var1].afterSetDead();
            }
        }
    }

    protected PlayerBase GetOverwrittenSetDead(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideSetDeadHooks.length; ++var2)
        {
            if (this.overrideSetDeadHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideSetDeadHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void setPositionAndRotation(EntityPlayerSP var0, double var1, double var3, double var5, float var7, float var8)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.setPositionAndRotation(var1, var3, var5, var7, var8);
        }
        else
        {
            var0.localSetPositionAndRotation(var1, var3, var5, var7, var8);
        }
    }

    private void setPositionAndRotation(double var1, double var3, double var5, float var7, float var8)
    {
        int var9;

        if (this.beforeSetPositionAndRotationHooks != null)
        {
            for (var9 = this.beforeSetPositionAndRotationHooks.length - 1; var9 >= 0; --var9)
            {
                this.beforeSetPositionAndRotationHooks[var9].beforeSetPositionAndRotation(var1, var3, var5, var7, var8);
            }
        }

        if (this.overrideSetPositionAndRotationHooks != null)
        {
            this.overrideSetPositionAndRotationHooks[this.overrideSetPositionAndRotationHooks.length - 1].setPositionAndRotation(var1, var3, var5, var7, var8);
        }
        else
        {
            this.player.localSetPositionAndRotation(var1, var3, var5, var7, var8);
        }

        if (this.afterSetPositionAndRotationHooks != null)
        {
            for (var9 = 0; var9 < this.afterSetPositionAndRotationHooks.length; ++var9)
            {
                this.afterSetPositionAndRotationHooks[var9].afterSetPositionAndRotation(var1, var3, var5, var7, var8);
            }
        }
    }

    protected PlayerBase GetOverwrittenSetPositionAndRotation(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideSetPositionAndRotationHooks.length; ++var2)
        {
            if (this.overrideSetPositionAndRotationHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideSetPositionAndRotationHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static EnumStatus sleepInBedAt(EntityPlayerSP var0, int var1, int var2, int var3)
    {
        EnumStatus var4;

        if (var0.playerAPI != null)
        {
            var4 = var0.playerAPI.sleepInBedAt(var1, var2, var3);
        }
        else
        {
            var4 = var0.localSleepInBedAt(var1, var2, var3);
        }

        return var4;
    }

    private EnumStatus sleepInBedAt(int var1, int var2, int var3)
    {
        if (this.beforeSleepInBedAtHooks != null)
        {
            for (int var4 = this.beforeSleepInBedAtHooks.length - 1; var4 >= 0; --var4)
            {
                this.beforeSleepInBedAtHooks[var4].beforeSleepInBedAt(var1, var2, var3);
            }
        }

        EnumStatus var6;

        if (this.overrideSleepInBedAtHooks != null)
        {
            var6 = this.overrideSleepInBedAtHooks[this.overrideSleepInBedAtHooks.length - 1].sleepInBedAt(var1, var2, var3);
        }
        else
        {
            var6 = this.player.localSleepInBedAt(var1, var2, var3);
        }

        if (this.afterSleepInBedAtHooks != null)
        {
            for (int var5 = 0; var5 < this.afterSleepInBedAtHooks.length; ++var5)
            {
                this.afterSleepInBedAtHooks[var5].afterSleepInBedAt(var1, var2, var3);
            }
        }

        return var6;
    }

    protected PlayerBase GetOverwrittenSleepInBedAt(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideSleepInBedAtHooks.length; ++var2)
        {
            if (this.overrideSleepInBedAtHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideSleepInBedAtHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void swingItem(EntityPlayerSP var0)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.swingItem();
        }
        else
        {
            var0.localSwingItem();
        }
    }

    private void swingItem()
    {
        int var1;

        if (this.beforeSwingItemHooks != null)
        {
            for (var1 = this.beforeSwingItemHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeSwingItemHooks[var1].beforeSwingItem();
            }
        }

        if (this.overrideSwingItemHooks != null)
        {
            this.overrideSwingItemHooks[this.overrideSwingItemHooks.length - 1].swingItem();
        }
        else
        {
            this.player.localSwingItem();
        }

        if (this.afterSwingItemHooks != null)
        {
            for (var1 = 0; var1 < this.afterSwingItemHooks.length; ++var1)
            {
                this.afterSwingItemHooks[var1].afterSwingItem();
            }
        }
    }

    protected PlayerBase GetOverwrittenSwingItem(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideSwingItemHooks.length; ++var2)
        {
            if (this.overrideSwingItemHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideSwingItemHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void updateCloak(EntityPlayerSP var0)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.updateCloak();
        }
        else
        {
            var0.localUpdateCloak();
        }
    }

    private void updateCloak()
    {
        int var1;

        if (this.beforeUpdateCloakHooks != null)
        {
            for (var1 = this.beforeUpdateCloakHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeUpdateCloakHooks[var1].beforeUpdateCloak();
            }
        }

        if (this.overrideUpdateCloakHooks != null)
        {
            this.overrideUpdateCloakHooks[this.overrideUpdateCloakHooks.length - 1].updateCloak();
        }
        else
        {
            this.player.localUpdateCloak();
        }

        if (this.afterUpdateCloakHooks != null)
        {
            for (var1 = 0; var1 < this.afterUpdateCloakHooks.length; ++var1)
            {
                this.afterUpdateCloakHooks[var1].afterUpdateCloak();
            }
        }
    }

    protected PlayerBase GetOverwrittenUpdateCloak(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideUpdateCloakHooks.length; ++var2)
        {
            if (this.overrideUpdateCloakHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideUpdateCloakHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void updateEntityActionState(EntityPlayerSP var0)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.updateEntityActionState();
        }
        else
        {
            var0.localUpdateEntityActionState();
        }
    }

    private void updateEntityActionState()
    {
        int var1;

        if (this.beforeUpdateEntityActionStateHooks != null)
        {
            for (var1 = this.beforeUpdateEntityActionStateHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeUpdateEntityActionStateHooks[var1].beforeUpdateEntityActionState();
            }
        }

        if (this.overrideUpdateEntityActionStateHooks != null)
        {
            this.overrideUpdateEntityActionStateHooks[this.overrideUpdateEntityActionStateHooks.length - 1].updateEntityActionState();
        }
        else
        {
            this.player.localUpdateEntityActionState();
        }

        if (this.afterUpdateEntityActionStateHooks != null)
        {
            for (var1 = 0; var1 < this.afterUpdateEntityActionStateHooks.length; ++var1)
            {
                this.afterUpdateEntityActionStateHooks[var1].afterUpdateEntityActionState();
            }
        }
    }

    protected PlayerBase GetOverwrittenUpdateEntityActionState(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideUpdateEntityActionStateHooks.length; ++var2)
        {
            if (this.overrideUpdateEntityActionStateHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideUpdateEntityActionStateHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void updateRidden(EntityPlayerSP var0)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.updateRidden();
        }
        else
        {
            var0.localUpdateRidden();
        }
    }

    private void updateRidden()
    {
        int var1;

        if (this.beforeUpdateRiddenHooks != null)
        {
            for (var1 = this.beforeUpdateRiddenHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeUpdateRiddenHooks[var1].beforeUpdateRidden();
            }
        }

        if (this.overrideUpdateRiddenHooks != null)
        {
            this.overrideUpdateRiddenHooks[this.overrideUpdateRiddenHooks.length - 1].updateRidden();
        }
        else
        {
            this.player.localUpdateRidden();
        }

        if (this.afterUpdateRiddenHooks != null)
        {
            for (var1 = 0; var1 < this.afterUpdateRiddenHooks.length; ++var1)
            {
                this.afterUpdateRiddenHooks[var1].afterUpdateRidden();
            }
        }
    }

    protected PlayerBase GetOverwrittenUpdateRidden(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideUpdateRiddenHooks.length; ++var2)
        {
            if (this.overrideUpdateRiddenHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideUpdateRiddenHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void writeEntityToNBT(EntityPlayerSP var0, NBTTagCompound var1)
    {
        if (var0.playerAPI != null)
        {
            var0.playerAPI.writeEntityToNBT(var1);
        }
        else
        {
            var0.localWriteEntityToNBT(var1);
        }
    }

    private void writeEntityToNBT(NBTTagCompound var1)
    {
        int var2;

        if (this.beforeWriteEntityToNBTHooks != null)
        {
            for (var2 = this.beforeWriteEntityToNBTHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeWriteEntityToNBTHooks[var2].beforeWriteEntityToNBT(var1);
            }
        }

        if (this.overrideWriteEntityToNBTHooks != null)
        {
            this.overrideWriteEntityToNBTHooks[this.overrideWriteEntityToNBTHooks.length - 1].writeEntityToNBT(var1);
        }
        else
        {
            this.player.localWriteEntityToNBT(var1);
        }

        if (this.afterWriteEntityToNBTHooks != null)
        {
            for (var2 = 0; var2 < this.afterWriteEntityToNBTHooks.length; ++var2)
            {
                this.afterWriteEntityToNBTHooks[var2].afterWriteEntityToNBT(var1);
            }
        }
    }

    protected PlayerBase GetOverwrittenWriteEntityToNBT(PlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideWriteEntityToNBTHooks.length; ++var2)
        {
            if (this.overrideWriteEntityToNBTHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideWriteEntityToNBTHooks[var2 - 1];
            }
        }

        return var1;
    }
}
