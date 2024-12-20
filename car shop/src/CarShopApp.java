import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//定义车辆类型 价格和评价三个私有属性
abstract class AbstractVehicle {
    private String vehicleModel;
    private double vehiclePrice;
    private String vehiclePerformance;

    public AbstractVehicle(String model, double price, String performance) {
        this.vehicleModel = model;
        this.vehiclePrice = price;
        this.vehiclePerformance = performance;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public String getVehiclePerformance() {
        return vehiclePerformance;
    }
}
//定义三种车的一种：轿车
class SedanVehicle extends AbstractVehicle {
    public SedanVehicle(String model, double price, String performance) {
        super(model, price, performance);
    }
}
//定义另一种：SUV
class SUVehicle extends AbstractVehicle {
    public SUVehicle(String model, double price, String performance) {
        super(model, price, performance);
    }
}
//定义最后一种：跑车
class CoupeVehicle extends AbstractVehicle {
    public CoupeVehicle(String model, double price, String performance) {
        super(model, price, performance);
    }
}

//网上商铺的分类
class OnlineCarShop {
    private List<SedanVehicle> sedanList; 
    private List<SUVehicle> suvList; 
    private List<CoupeVehicle> coupeList; 

    public OnlineCarShop() {
        sedanList = new ArrayList<>();
        suvList = new ArrayList<>();
        coupeList = new ArrayList<>();
    //录入各种车的数据和名称
        sedanList.add(new SedanVehicle("1.丰田卡罗拉", 12.5, "世界销量冠军"));
        sedanList.add(new SedanVehicle("2.奥迪A4", 29.9, "A4 A4 你猜奥迪销量冠军是谁"));
        sedanList.add(new SedanVehicle("3.大众朗逸", 13.9, "底盘好，省油耐用，适合家庭出行"));
        sedanList.add(new SedanVehicle("4.蔚来ET7", 55.6, "made in China"));
        
        suvList.add(new SUVehicle("1.丰田RAV4", 17.8, "硬汉"));
        suvList.add(new SUVehicle("2.奥迪Q7", 69.8, "成熟女性的第一选！"));
        suvList.add(new SUVehicle("3.路虎揽胜", 150.2, "开上路虎揽胜，你将不再拥有自卑"));
        suvList.add(new SUVehicle("4.保时捷Cayenne", 99.6, "运动性能和豪华舒适完美结合"));
        
        coupeList.add(new CoupeVehicle("1.马自达MX-7", 50.5, "什么叫做情怀！"));
        coupeList.add(new CoupeVehicle("2.丰田supra", 58.6, "这辆更是情怀拉满"));
        coupeList.add(new CoupeVehicle("3.保时捷911", 208.9, "它可是911啊"));
        coupeList.add(new CoupeVehicle("4.奥迪RS6", 138.8, "奥迪RS6，开上你也666"));
    }
//展示出可供选择的三种车型
    public void showAvailableVehicleTypes() {
        System.out.println("我们为您提供以下几种车辆类型，供您选择：");
        System.out.println("1. 轿车");
        System.out.println("2. SUV");
        System.out.println("3. 轿跑");
    }
//根据所选车型展示车辆数据
    public void displayVehiclesAndGetChoice(String vehicleType, Scanner inputScanner) {
        List<? extends AbstractVehicle> vehicleList = getVehicleListByType(vehicleType);
        System.out.println("以下是 " + vehicleType + " 类在售车辆信息：");
        for (AbstractVehicle vehicle : vehicleList) {
            System.out.println("车型号: " + vehicle.getVehicleModel());
            System.out.println("价格: " + vehicle.getVehiclePrice()+"万元");
            System.out.println("性能: " + vehicle.getVehiclePerformance());
            System.out.println("-----------------------------------------");
        }
        //让顾客选择一辆心仪的车并送上祝福
        System.out.println("请输入您较为满意的车辆 " + vehicleType + " 车型号：");
        String selectedModel = inputScanner.next();
        displaySelectedVehicleDetails(vehicleList, selectedModel);
        System.out.println("真是个明智的选择，祝您购车愉快！");
    }

    private List<? extends AbstractVehicle> getVehicleListByType(String type) {
        if ("轿车".equals(type)) {
            return sedanList;
        } else if ("SUV".equals(type)) {
            return suvList;
        } else if ("轿跑".equals(type)) {
            return coupeList;
        }
        return new ArrayList<>();
    }

    private void displaySelectedVehicleDetails(List<? extends AbstractVehicle> vehicleList, String selectedModel) {
        for (AbstractVehicle vehicle : vehicleList) {
            if (selectedModel.equals(vehicle.getVehicleModel())) {
                System.out.println("您选择的车辆信息：");
                System.out.println("车型号: " + vehicle.getVehicleModel());
                System.out.println("价格: " + vehicle.getVehiclePrice() + "万元");
                System.out.println("性能: " + vehicle.getVehiclePerformance());
            }
        }
    }
}
//
public class CarShopApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OnlineCarShop carShop = new OnlineCarShop();

        carShop.showAvailableVehicleTypes();
        System.out.println("请输入您想查看的车型对应的数字编号：");
        int choice = scanner.nextInt();
        String selectedVehicleType = getSelectedVehicleType(choice);
        if (selectedVehicleType == null) {
            System.out.println("输入的选项无效，请重新输入。");
            return;
        }
        carShop.displayVehiclesAndGetChoice(selectedVehicleType, scanner);

        scanner.close();
    }
    private static String getSelectedVehicleType(int choice) {
        return switch (choice) {
            case 1 -> "轿车";
            case 2 -> "SUV";
            case 3 -> "轿跑";
            default -> null;
        };
    }

}

