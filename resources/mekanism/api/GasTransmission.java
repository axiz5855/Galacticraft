package mekanism.api;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.vector.Vector3;

public class GasTransmission 
{
    /**
     * Gets all the tubes around a tile entity.
     * @param tileEntity - center tile entity
     * @return array of TileEntities
     */
    public static TileEntity[] getConnectedTubes(TileEntity tileEntity)
    {
    	TileEntity[] tubes = new TileEntity[] {null, null, null, null, null, null};
    	
    	for(ForgeDirection orientation : ForgeDirection.values())
    	{
    		if(orientation != ForgeDirection.UNKNOWN)
    		{
    			TileEntity tube = Vector3.getTileEntityFromSide(tileEntity.worldObj, new Vector3(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord), orientation);
    			
    			if(tube instanceof IPressurizedTube && ((IPressurizedTube)tube).canTransferGas())
    			{
    				tubes[orientation.ordinal()] = tube;
    			}
    		}
    	}
    	
    	return tubes;
    }
    
    /**
     * Gets all the acceptors around a tile entity.
     * @param tileEntity - center tile entity
     * @return array of IGasAcceptors
     */
    public static IGasAcceptor[] getConnectedAcceptors(TileEntity tileEntity)
    {
    	IGasAcceptor[] acceptors = new IGasAcceptor[] {null, null, null, null, null, null};
    	
    	for(ForgeDirection orientation : ForgeDirection.values())
    	{
    		if(orientation != ForgeDirection.UNKNOWN)
    		{
    			TileEntity acceptor = Vector3.getTileEntityFromSide(tileEntity.worldObj, new Vector3(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord), orientation);
    			
    			if(acceptor instanceof IGasAcceptor)
    			{
    				acceptors[orientation.ordinal()] = (IGasAcceptor)acceptor;
    			}
    		}
    	}
    	
    	return acceptors;
    }
    
    /**
     * Gets all the tube connections around a tile entity.
     * @param tileEntity - center tile entity
     * @return array of ITubeConnections
     */
    public static ITubeConnection[] getConnections(TileEntity tileEntity)
    {
    	ITubeConnection[] connections = new ITubeConnection[] {null, null, null, null, null, null};
    	
    	for(ForgeDirection orientation : ForgeDirection.values())
    	{
    		if(orientation != ForgeDirection.UNKNOWN)
    		{
    			TileEntity connection = Vector3.getTileEntityFromSide(tileEntity.worldObj, new Vector3(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord), orientation);
    			
    			if(connection instanceof ITubeConnection)
    			{
    				connections[orientation.ordinal()] = (ITubeConnection)connection;
    			}
    		}
    	}
    	
    	return connections;
    }
    
    /**
     * Emits a defined gas to the network.
     * @param type - gas type to send
     * @param amount - amount of gas to send
     * @param sender - the sender of the gas
     * @param facing - side the sender is outputting from
     * @return rejected gas
     */
    public static int emitGasToNetwork(EnumGas type, int amount, TileEntity sender, ForgeDirection facing)
    {
    	TileEntity pointer = Vector3.getTileEntityFromSide(sender.worldObj, new Vector3(sender.xCoord, sender.yCoord, sender.zCoord), facing);
    	
    	if(pointer != null)
    	{
	    	GasTransferProtocol calculation = new GasTransferProtocol(pointer, type, amount);
	    	return calculation.calculate();
    	}
    	
    	return amount;
    }
}
