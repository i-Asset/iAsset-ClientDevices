package org.srfg.conveyorbelt;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

import at.srfg.iot.common.datamodel.asset.aas.basic.Submodel;
import at.srfg.iot.common.datamodel.asset.aas.common.referencing.KeyElementsEnum;
import at.srfg.iot.common.datamodel.asset.aas.common.referencing.Kind;
import at.srfg.iot.common.datamodel.asset.aas.common.types.DataTypeEnum;
import at.srfg.iot.common.datamodel.asset.aas.modeling.submodelelement.Property;
import at.srfg.iot.common.datamodel.asset.aas.common.referencing.Reference;

import com.digitalpetri.opcua.stack.core.types.builtin.Variant;
import org.srfg.basedevice.BaseDevice;
import org.srfg.conveyorbelt.opcua.OPCUAManager;



/********************************************************************************************************
 * This class implements the smart manufacturing class for our lab conveyor belt
 * 
 * @author mathias.schmoigl
 ********************************************************************************************************/
public class ConveyorBelt extends BaseDevice {

	private OPCUAManager opcuaManager;
	private BeltListener listener;
	private boolean active;

	// writable
	private float movebelt;
	private boolean switchbusylight;

	// readable
	private String beltstate = "";
	private String beltdist = "";
	private boolean beltmoving;

	/*********************************************************************************************************
	 * CTOR
	 ********************************************************************************************************/
	public ConveyorBelt() { this.opcuaManager = new OPCUAManager(this); }

	@Override
	public String getName() {return "belt";}

	@Override
	public String getDirectory() {return "/lab/belt/belt01";}

	/*********************************************************************************************************
	 * Listener
	 ********************************************************************************************************/
	public BeltListener getListener() {
		return listener;
	}
	public void setListener(BeltListener listener) {
		this.listener = listener;
	}

	/*********************************************************************************************************
	 * movebelt
	 ********************************************************************************************************/
	public float getMoveBelt() {
		return movebelt;
	}
	public void setMoveBelt(float newSpeed) {

		if (this.movebelt != newSpeed)
			opcuaManager.WriteValue(OPCUAManager.WriteLocation.MoveBelt, new Variant(newSpeed));

		this.movebelt = newSpeed;
		if (listener != null) {
			listener.MoveBeltChanged();
		}
	}

	/*********************************************************************************************************
	 * switchbusylight
	 ********************************************************************************************************/
	public boolean getSwitchBusyLight() {
		return switchbusylight;
	}
	public void setSwitchBusyLight(boolean newLightValue) {

		if(this.switchbusylight != newLightValue)
			opcuaManager.WriteValue(OPCUAManager.WriteLocation.Switchlight, new Variant(newLightValue));

		this.switchbusylight = newLightValue;
		if (listener != null) {
			listener.SwitchBusyLightChanged();
		}
	}

	/*********************************************************************************************************
	 * beltstate
	 ********************************************************************************************************/
	public String getBeltState() {
		return beltstate;
	}
	public void setBeltState(String state) {
		this.beltstate = state;
		if (listener != null) {
			listener.ConBeltStateChanged();
		}
	}

	/*********************************************************************************************************
	 * beltdist
	 ********************************************************************************************************/
	public String getBeltDist() {
		return beltdist;
	}
	public void setBeltDist(String dist) {
		this.beltdist = dist;
		if (listener != null) {
			listener.ConBeltDistChanged();
		}
	}

	/*********************************************************************************************************
	 * beltmoving
	 ********************************************************************************************************/
	public boolean getBeltMoving() {
		return beltmoving;
	}
	public void setBeltMoving(boolean moving) {
		this.beltmoving = moving;
		if (listener != null) {
			listener.ConBeltMovingChanged();
		}
	}


	/*********************************************************************************************************
	 * BeltRunner
	 ********************************************************************************************************/
	private class BeltRunner implements Runnable {

		@Override
		public void run() {
			while (active) {
				try {
					Thread.sleep(1000);
					// the speed is the distance per second

					//distanceRun += speed;
					if (listener != null) {
						//listener.distanceChanged();
					}
				} catch (InterruptedException e) {
					//
				}
			}
		}
	}

	/*********************************************************************************************************
	 * start
	 ********************************************************************************************************/
	@Override
	public void start() {
		if (!isActive()) {

			this.opcuaManager.StartReadThread();
			active = true;

			// init read properties
			this.setBeltState("0");
			this.setBeltDist("0");
			this.setBeltMoving(false);

			new Thread(new BeltRunner()).start();
		}
	}

	/*********************************************************************************************************
	 * stop
	 ********************************************************************************************************/
	@Override
	public void stop() {
		if (isActive()) {
			this.opcuaManager.StopReadThread();
            active = false;
		}
	}

	/*********************************************************************************************************
	 * Active
	 ********************************************************************************************************/
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		if (active != this.active) {
			if (active) {
				start();
			} else {
				stop();
			}
		}
		// no effect
		this.active = active;
	}

	/*********************************************************************************************************
	 * createIdentification
	 ********************************************************************************************************/
	@Override
	protected Submodel createIdentification() {

		// create the sub model
		Submodel info = new Submodel();
		info.setIdShort("identification");
		info.setSemanticId(new Reference("27380107", KeyElementsEnum.Submodel)); // e-class-ID "Roboterarm"
		info.setDescription("de", "Herstellerinformationen");
		info.setKind(Kind.Instance);
		//info.setSemanticId(new Reference(new Key(KeyElements.SUBMODEL, false, "0173-1#02-AAV232#002", KeyType.IRDI)));

		Property manufacturer = new Property();
		manufacturer.setIdShort("manufacturer");
		manufacturer.setValue("FRANKA EMIKA Gmbh");
		manufacturer.setSemanticId(new Reference("0173-1#02-AAO677#002", KeyElementsEnum.Property));
		info.addSubmodelElement(manufacturer);

		Property gln = new Property();
		gln.setIdShort("gln");
		gln.setValue("GLN-Number Coneyor Belt");
		gln.setSemanticId(new Reference("0173-1#02-AAY812#001", KeyElementsEnum.Property));
		info.addSubmodelElement(gln);

		Property productFamily = new Property();
		productFamily.setIdShort("productFamily");
		productFamily.setValue("research robot arm");
		productFamily.setSemanticId(new Reference("0173-1#02-AAY812#001", KeyElementsEnum.Property));

		info.addSubmodelElement(productFamily);
		return info;
	}

	/*********************************************************************************************************
	 * createProperties
	 *
	 * INFO: For lambda properties, the type has to be explicitly specified as it
	 * can not be retrieved from supplier automatically
	 ********************************************************************************************************/
	@Override
	protected Submodel createProperties() {

		Submodel info = new Submodel();
		info.setIdShort("properties");
		info.setDescription("de", "Statusinformationen");
		info.setKind(Kind.Instance);

		// (ReadWriteAccess)
		Property movebelt = new Property();
		movebelt.setIdShort("movebelt");
		movebelt.setDescription("de", "Förderband bewegen");
		movebelt.setGetter(() -> { // Supplier Function (Getter)
			return String.valueOf(this.getMoveBelt());
		});
		movebelt.setSetter((args) -> { // Consumer Function (Setter)
			this.setMoveBelt(Float.parseFloat(args));
		});
		movebelt.setValueQualifier(DataTypeEnum.DECIMAL); // DOUBLE
		info.addSubmodelElement(movebelt);


		Property switchbusylight = new Property();
		switchbusylight.setIdShort("switchbusylight");
		switchbusylight.setDescription("de", "Warnlampenstatus ändern");
		switchbusylight.setGetter(() -> { // Supplier Function (Getter)
			return String.valueOf(this.getSwitchBusyLight());
		});
		switchbusylight.setSetter((args) -> { // Consumer Function (Setter)
			this.setSwitchBusyLight(Boolean.parseBoolean(args));
		});
		switchbusylight.setValueQualifier(DataTypeEnum.DECIMAL); // DOUBLE
		info.addSubmodelElement(switchbusylight);


		// (ReadOnlyAccess)
		Property beltState = new Property();
		beltState.setIdShort("beltstate");
		beltState.setDescription("de", "BeltState");
		beltState.setGetter(this::getBeltState);
		beltState.setValueQualifier(DataTypeEnum.DECIMAL); // FLOAT
		info.addSubmodelElement(beltState);

		Property beltDist = new Property();
		beltDist.setIdShort("beltdist");
		beltDist.setDescription("de", "BeltDist");
		beltDist.setGetter(this::getBeltDist);
		beltDist.setValueQualifier(DataTypeEnum.DECIMAL); // FLOAT
		info.addSubmodelElement(beltDist);

		Property beltMoving = new Property();
		beltMoving.setIdShort("beltmoving");
		beltMoving.setDescription("de", "BeltMoving");
		beltMoving.setGetter(this::getBeltDist);
		beltMoving.setValueQualifier(DataTypeEnum.BOOLEAN);
		info.addSubmodelElement(beltMoving);

		return info;
	}

	/*********************************************************************************************************
	 * createModel
	 ********************************************************************************************************/
	@Override
	protected Map<String, Object> createModel() {

		Map<String, Object> properties = new HashMap<>();
		properties.put("id", this.getName() + "01");
		properties.put("desc", "Model connected with the edge device");

		// add movebelt property
		Supplier<Object> lambdaFunction = () -> this.getMoveBelt();
		properties.put("movebelt", lambdaFunction);

		// add switchbusylight property
		Supplier<Object> lambdaFunction1 = () -> this.getSwitchBusyLight();
		properties.put("switchbusylight", lambdaFunction1);

		// add beltstate property
		Supplier<Object> lambdaFunction3 = () -> this.getBeltState();
		properties.put("beltstate", lambdaFunction3);

		// add beltdist property
		Supplier<Object> lambdaFunction4 = () -> this.getBeltDist();
		properties.put("beltdist", lambdaFunction4);

		// add beltmoving property
		Supplier<Object> lambdaFunction5 = () -> this.getBeltMoving();
		properties.put("beltmoving", lambdaFunction5);


		// Create an empty container for custom operations
		Map<String, Object> operations = new HashMap<>();
		Function<Object, Object> activateFunction = (args) -> {
			this.setActive(true);
			return null;
		};
		operations.put("start", activateFunction);

		// Add a function that deactivates the oven and implements a functional interface
		Function<Object, Object> deactivateFunction = (args) -> {
			this.setActive(false);
			return null;
		};
		operations.put("stop", deactivateFunction);

		// Add a function
		Function<Object, Object> setSpeedFunction = (args) -> {
			Object[] params = (Object[]) args;
			if (params.length == 1) {
				this.setMoveBelt(((Double) params[0]).floatValue());
			}
			return null;
		};
		operations.put("setSpeed", setSpeedFunction);

		Function<Object, Object> setLightFunction = (args) -> {
			Object[] params = (Object[]) args;
			if (params.length == 1) {
				this.setSwitchBusyLight(((boolean) params[0]));
			}
			return null;
		};
		operations.put("setLight", setLightFunction);

		Map<String, Object> myModel = new HashMap<>();
		myModel.put("operations", operations);
		myModel.put("properties", properties);
		return myModel;
	}
}
