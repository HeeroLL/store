package com.zebone.tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 文档订阅任务设置的工具
 *
 * @author 杨英
 * @version 2014-8-14 上午8:45
 */
public class TaskSet extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private String path = null;
    private Map<Integer, String> map = new HashMap<Integer, String>();
    private JPanel mainPanel = null;
    private JPanel btPanel = null;
    private JLabel orgLabel = null;
    private JLabel timeLabel = null;
    private JLabel docTypeLabel = null;
    private JLabel startDtLabel = null;
    private JLabel endDtLabel = null;
    private JTextField timeField = null;
    private JTextField orgField = null;
    private JTextField docTypeField = null;
    private JTextField startDtField = null;
    private JTextField endDtField = null;
    private JButton ConfirmBt = null;
    private JButton CancelBt = null;
    private JComboBox laf = null;

    public TaskSet() {
        init();
    }

    /**
     * 填充下拉列表
     *
     * @return Vector<String>
     */
    private Vector<String> initComboBos() {
        //获得所有java自带的样式
        UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
        Vector<String> lafs = new Vector<String>();
        for (int i = 0; i < lafInfo.length; i++) {
            //将样式存入map中，以便在更改样式时能找到。
            map.put(i, lafInfo[i].getClassName());
            String[] temp = lafInfo[i].getClassName().split("\\.");
            String str = temp[temp.length - 1].replace("LookAndFeel", "");
            lafs.addElement(str);
        }
        return lafs;
    }

    @SuppressWarnings("static-access")
    private void init() {
        this.mainPanel = (JPanel) this.getContentPane();
        this.mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.btPanel = new JPanel();

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.orgLabel = new JLabel("请输入订阅文档机构代码");
        this.mainPanel.add(this.orgLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.orgField = new JTextField(20);
        this.mainPanel.add(this.orgField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.docTypeLabel = new JLabel("请输入订阅的文档类型");
        this.mainPanel.add(this.docTypeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.docTypeField = new JTextField(20);
        this.mainPanel.add(this.docTypeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.startDtLabel = new JLabel("请输入卫生服务活动起始日期(如:20140808)");
        this.mainPanel.add(this.startDtLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        this.startDtField = new JTextField(20);
        this.mainPanel.add(this.startDtField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.endDtLabel = new JLabel("请输入卫生服务活动截止日期(如:20140808)");
        this.mainPanel.add(this.endDtLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        this.endDtField = new JTextField(20);
        this.mainPanel.add(this.endDtField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        this.timeLabel = new JLabel("请输入任务启动时间(如:10:22,15:00)");
        this.mainPanel.add(this.timeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        this.timeField = new JTextField(20);
        this.mainPanel.add(this.timeField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        this.ConfirmBt = new JButton("确定");
        this.ConfirmBt.addActionListener(this);
        this.CancelBt = new JButton("取消");
        this.CancelBt.addActionListener(this);
        this.btPanel.add(this.ConfirmBt);
        this.btPanel.add(this.CancelBt);
        this.mainPanel.add(this.btPanel, gbc);


        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.height - 200) / 2;
        int y = (d.width - 500) / 2;
        this.setBounds(x, y, 500, 200);
        this.setTitle("文档订阅任务设置工具");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        //点击确定按钮执行的操作
        if ("确定".equals(event.getActionCommand())) {
            System.out.println("确定");
            SetSystemProperty setSystemProperty = new SetSystemProperty();
            String orgCode = this.orgField.getText();
            String docType = this.docTypeField.getText();
            String startDt = this.startDtField.getText();
            String endDt = this.endDtField.getText();
            String time = this.timeField.getText();
            //更新属性文件
            if(orgCode!=null && !"".equals(orgCode)){
                setSystemProperty.updateProperties("docOrg",orgCode);
            }
            if(docType!=null && !"".equals(docType)){
                setSystemProperty.updateProperties("docType",docType);
            }
            if(startDt!=null && !"".equals(startDt)){
                setSystemProperty.updateProperties("startDt",startDt);
            }
            if(endDt!=null && !"".equals(endDt)){
                setSystemProperty.updateProperties("endDt",endDt);
            }
            if(time!=null && !"".equals(time)){
                String executeTime = "";
                String[] timeLic = time.split(",");
                for(int i=0; i<timeLic.length; i++){
                    if(!"".equals(executeTime)){
                        executeTime = executeTime +",";
                    }
                    String strTime = timeLic[i]; //格式为 10:30
                    //把输入时间组装成定时任务执行时间  如 0 58 15 * * ?
                    executeTime = executeTime + "0 "+strTime.substring(3,5)+" "+strTime.substring(0,2)+" * * ?";
                }
                setSystemProperty.updateProperties("executeTime",executeTime);
            }
        }
        if ("取消".equals(event.getActionCommand())) {
            System.exit(0);
        }

    }


    /**
     * @param args
     * @throws
     */
    public static void main(String[] args) {
        new TaskSet();
    }

}
