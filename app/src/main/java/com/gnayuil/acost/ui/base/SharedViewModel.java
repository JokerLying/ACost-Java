package com.gnayuil.acost.ui.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gnayuil.acost.data.bean.InfoItem;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<List<InfoItem>> infoList = new MutableLiveData<>();

    private DecimalFormat costFormat = new DecimalFormat("0.##");

    public SharedViewModel() {
        initData();
    }

    public MutableLiveData<List<InfoItem>> getInfoList() {
        return infoList;
    }

    public void addItem() {
        if (infoList.getValue() == null) {
            initData();
        }
        InfoItem addOne = new InfoItem();
        addOne.setStatus(InfoItem.ItemStatus.ADD);
        infoList.getValue().add(addOne);
        infoList.setValue(infoList.getValue());
    }

    private void initData() {
        List<InfoItem> infoList = new ArrayList<>();
        InfoItem realCost = new InfoItem();
        realCost.setCheck(true);
        infoList.add(realCost);
        InfoItem packetOne = new InfoItem();
        infoList.add(packetOne);
        this.infoList.setValue(infoList);
    }

    public void clickOne(String str) {
        if (infoList.getValue() == null) {
            initData();
        }
        int location = 0;
        for (int i = 0; i < infoList.getValue().size(); i++) {
            InfoItem one = infoList.getValue().get(i);
            if (one.isCheck()) {
                location = i;
                break;
            }
        }
        InfoItem item = infoList.getValue().get(location);
        switch (str) {
            case "+":
                plus(item);
                break;
            case ".":
                dot(item);
                break;
            case "DEL":
                del(item);
                break;
            default:
                numeral(item, str);
                break;
        }
        item.setStatus(InfoItem.ItemStatus.MODIFY);
        item.setConsole(costFormat.format(calculateCost(item)));
        updatePacketsCost();
        infoList.setValue(infoList.getValue());
    }

    private BigDecimal calculateCost(InfoItem item) {
        String[] cost = item.getLambda().split("\\+");
        BigDecimal result = new BigDecimal("0");
        for (String one :
                cost) {
            result = result.add(new BigDecimal(one.lastIndexOf(".") == one.length() - 1 ? one + "0" : one));
        }
        return result;
    }

    private void updatePacketsCost() {
        if (infoList.getValue() == null) {
            return;
        }
        String realCost = infoList.getValue().get(0).getConsole();
        if ("0".equals(realCost)) {
            return;
        }
        BigDecimal allPacket = calculateCost(infoList.getValue().get(1));
        for (int i = 2; i < infoList.getValue().size(); i++) {
            InfoItem one = infoList.getValue().get(i);
            allPacket = allPacket.add(calculateCost(one));
        }
        if (allPacket.floatValue() == 0) {
            return;
        }
        for (int i = 1; i < infoList.getValue().size(); i++) {
            InfoItem one = infoList.getValue().get(i);
            BigDecimal console = calculateCost(one);
            console = console.divide(allPacket, 4, BigDecimal.ROUND_HALF_UP);
            console = console.multiply(new BigDecimal(realCost));
            if (!costFormat.format(console).equals(one.getConsole())) {
                one.setConsole(costFormat.format(console));
                one.setStatus(InfoItem.ItemStatus.MODIFY);
            }
        }
    }

    private void plus(InfoItem item) {
        if (item.getLambda().lastIndexOf("+") != item.getLambda().length() - 1) {
            if (item.getLambda().lastIndexOf(".") == item.getLambda().length() - 1) {
                item.setLambda(item.getLambda().substring(0, item.getLambda().length() - 1) + "+0");
            } else {
                item.setLambda(item.getLambda() + "+0");
            }
        }
    }

    private void dot(InfoItem item) {
        String[] cost = item.getLambda().split("\\+");
        if (!cost[cost.length - 1].contains(".")) {
            item.setLambda(item.getLambda() + ".");
        }
    }

    private void del(InfoItem item) {
        String[] cost = item.getLambda().split("\\+");
        if (cost.length > 1) {
            if (cost[cost.length - 1].length() == 1) {
                if ("0".equals(cost[cost.length - 1])) {
                    item.setLambda(item.getLambda().substring(0, item.getLambda().length() - 2));
                } else {
                    item.setLambda(item.getLambda().substring(0, item.getLambda().length() - 1) + "0");
                }
            } else {
                item.setLambda(item.getLambda().substring(0, item.getLambda().length() - 1));
            }
        } else {
            if (item.getLambda().length() == 1) {
                item.setLambda("0");
            } else {
                item.setLambda(item.getLambda().substring(0, item.getLambda().length() - 1));
            }
        }
    }

    private void numeral(InfoItem item, String num) {
        String[] cost = item.getLambda().split("\\+");
        if (cost.length > 1) {
            if ("0".equals(cost[cost.length - 1].substring(cost[cost.length - 1].length() - 1)) && cost[cost.length - 1].length() == 1) {
                item.setLambda(item.getLambda().substring(0, item.getLambda().length() - 1) + num);
            } else {
                item.setLambda(item.getLambda() + num);
            }
        } else {
            if ("0".equals(item.getLambda())) {
                item.setLambda(num);
            } else {
                item.setLambda(item.getLambda() + num);
            }
        }
    }

}
