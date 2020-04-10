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
        infoList.getValue().add(new InfoItem());
        infoList.setValue(infoList.getValue());
    }

    private void initData() {
        List<InfoItem> infoList = new ArrayList<>();
        InfoItem realCost = new InfoItem();
        realCost.setCheck(true);
        infoList.add(realCost);
        InfoItem originalCost = new InfoItem();
        infoList.add(originalCost);
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
        calculateCost(item);
        infoList.setValue(infoList.getValue());
    }

    private void calculateCost(InfoItem item) {
        String[] cost = item.getLambda().split("\\+");
        BigDecimal result = new BigDecimal("0");
        for (String one :
                cost) {
            result = result.add(new BigDecimal(one.lastIndexOf(".") == one.length() - 1 ? one + "0" : one));
        }
        DecimalFormat format = new DecimalFormat("0.##");
        item.setConsole(format.format(result));

        if (infoList.getValue() == null) {
            return;
        }
        String realCost = infoList.getValue().get(0).getConsole();
        String originalCost = infoList.getValue().get(1).getConsole();
        if ("0".equals(realCost) || "0".equals(originalCost)) {
            return;
        }
        for (int i = 2; i < infoList.getValue().size(); i++) {
            InfoItem one = infoList.getValue().get(i);
            BigDecimal console = new BigDecimal(one.getConsole());
            console = console.divide(new BigDecimal(originalCost), 4, BigDecimal.ROUND_HALF_UP);
            console = console.multiply(new BigDecimal(realCost));
            one.setConsole(format.format(console));
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
