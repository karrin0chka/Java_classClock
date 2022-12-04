package org.example;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyFrame extends JFrame {
    JFrame frame = new JFrame();
    Shop sh = new Shop();
    ButtonGroup clGroup = new ButtonGroup();
    ArrayList<JRadioButton> masRB = new ArrayList<>();
    JPanel myPanel = new JPanel();
    JLabel lbMaxPrice = new JLabel();
    JLabel lbMaxCounter = new JLabel();
    JLabel lbAllStamp = new JLabel();
    JLabel Hour = new JLabel();
    JLabel Minute = new JLabel();
    JLabel Second = new JLabel();
    JLabel razde2 = new JLabel(":");
    JLabel Description = new JLabel();
    int index = 0;
    JSlider sliderH = new JSlider(0,0, 23, 0);
    JSlider sliderM = new JSlider(0,0, 59, 0);
    JSlider sliderS = new JSlider(0,0, 59, 0);
    JLabel lbSliderS = new JLabel("Секунды: ");
    JTextField TFAddS = new JTextField(5);
    JLabel LbS = new JLabel("Секунды: ");
    JButton BtnAddClock = new JButton("Добавить часы");

    public class MyRadioButton extends JFrame implements ActionListener {
        MyRadioButton() {
            int x = 0, y = 0;
            for (int i = 0; i < sh.listClocks.size(); i++) {
                JRadioButton cl = new JRadioButton(sh.listClocks.get(i).toString());
                masRB.add(cl);
                clGroup.add(cl);
                myPanel.add(cl);
                cl.addActionListener(this);
                y += 30;
            }
            myPanel.setBounds(0, 0, 300, 400);
            myPanel.setLayout(new GridLayout(15, 1));
            frame.add(myPanel, BorderLayout.SOUTH);
        }
        MyRadioButton(Clock cl) {
            JRadioButton RbtAddCl = new JRadioButton(cl.toString());
            RbtAddCl.addActionListener(this);
            masRB.add(RbtAddCl);
            clGroup.add(RbtAddCl);
            myPanel.add(RbtAddCl);
            RbtAddCl.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            index = masRB.indexOf(e.getSource());
            Description.setText(sh.listClocks.get(index).getStamp() + "   " + sh.listClocks.get(index).getPrice());
            sliderH.setValue(sh.listClocks.get(index).hour);
            sliderM.setValue(sh.listClocks.get(index).minute);

            if (sh.listClocks.get(index) instanceof ClockSec) {
                razde2.setVisible(true);
                Second.setVisible(true);
                sliderS.setValue(((ClockSec) sh.listClocks.get(index)).second);

                sliderS.setVisible(true);
                lbSliderS.setVisible(true);

                LbS.setVisible(true);
                TFAddS.setVisible(true);
            }
            else {
                razde2.setVisible(false);
                Second.setVisible(false);

                sliderS.setVisible(false);
                lbSliderS.setVisible(false);

                LbS.setVisible(false);
                TFAddS.setVisible(false);
            }
        }
    }
    void AddClock() {
        int x = 10, y = 400;
        JLabel LbAddClock = new JLabel("Добавить часы:");
        LbAddClock.setBounds(x, y, 150, 20);
        frame.add(LbAddClock);

        JLabel LbSt = new JLabel("Бренд: ");
        LbSt.setBounds(x, y + 30, 70, 20);
        frame.add(LbSt);
        JTextField TFSt = new JTextField(5);
        TFSt.setBounds(x + 70, y + 30, 110, 20);
        frame.add(TFSt);

        JLabel LbPr = new JLabel("Стоимость: ");
        LbPr.setBounds(x, y + 60, 70, 20);
        frame.add(LbPr);
        JTextField TFPr = new JTextField(5);
        TFPr.setBounds(x + 70, y + 60, 110, 20);
        frame.add(TFPr);

        JLabel LbT = new JLabel("Время: ");
        LbT.setBounds(x, y + 90, 70, 20);
        frame.add(LbT);

        JTextField TFH = new JTextField(5);
        TFH.setBounds(x + 70, y + 90, 28, 20);
        frame.add(TFH);

        JLabel razde1 = new JLabel(":");
        razde1.setBounds(x + 100, y + 87, 5, 20);
        frame.add(razde1);

        JTextField TFM = new JTextField(5);
        TFM.setBounds(x + 110, y + 90, 28, 20);
        frame.add(TFM);

        JLabel razde2 = new JLabel(":");
        razde2.setBounds(x + 140, y + 87, 5, 20);
        frame.add(razde2);

        JTextField TFS = new JTextField(5);
        TFS.setBounds(x + 150, y + 90, 28, 20);
        frame.add(TFS);

        BtnAddClock.setBounds(x + 15, y + 120, 150, 20);
        frame.add(BtnAddClock);

        BtnAddClock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e1) {
                try {
                    if(!TFS.getText().isEmpty()) {
                        ClockSec cl = new ClockSec(TFSt.getText(), Integer.parseInt(TFPr.getText()));
                        cl.SetTime(Hand.HOUR, Integer.parseInt(TFH.getText()));
                        cl.SetTime(Hand.MINUTE, Integer.parseInt(TFM.getText()));
                        cl.SetTime(Hand.SECOND, Integer.parseInt(TFS.getText()));
                        sh.listClocks.add(cl);
                        new MyRadioButton(cl);
                    }
                    else {
                        Clock cl = new Clock(TFSt.getText(), Integer.parseInt(TFPr.getText()));
                        cl.SetTime(Hand.HOUR, Integer.parseInt(TFH.getText()));
                        cl.SetTime(Hand.MINUTE, Integer.parseInt(TFM.getText()));
                        sh.listClocks.add(cl);
                        new MyRadioButton(cl);
                    }
                    lbMaxPrice.setText(sh.getClockMax().toString());
                    lbMaxCounter.setText(sh.MaxCounter().getKey());
                    lbAllStamp.setText(sh.seporateClocks().toString());
                } catch (ThrowOutException ex) {
                    JOptionPane.showConfirmDialog(null, "Некорректный ввод", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                    TFSt.setText("");
                    TFPr.setText("");
                    TFH.setText("");
                    TFM.setText("");
                    TFS.setText("");
                    throw new RuntimeException(ex);
                }
                TFSt.setText("");
                TFPr.setText("");
                TFH.setText("");
                TFM.setText("");
                TFS.setText("");
            }
        });
    }
    void MaxPriceClock() {
        int x = 300, y = 0;
        JLabel lbMaxCl = new JLabel("Описание самых дорогих часов:");
        lbMaxCl.setBounds(x, y, 250, 100);

        lbMaxPrice = new JLabel(sh.getClockMax().toString());
        lbMaxPrice.setBounds(x, y + 20, 250, 100);
        frame.add(lbMaxPrice);
        frame.add(lbMaxCl);
    }
    void MaxCounter() {
        int x = 300, y = 70;
        JLabel lbMaxC = new JLabel("Наиболее часто встречающаяся марка:");
        lbMaxC.setBounds(x, y, 250, 100);

        lbMaxCounter = new JLabel(sh.MaxCounter().getKey().toString());
        lbMaxCounter.setBounds(x, y + 20, 250, 100);
        frame.add(lbMaxC);
        frame.add(lbMaxCounter);
    }
    void AllStamp() {
        int x = 300, y = 140;
        JLabel lbAllSt = new JLabel("Марки часов:");
        lbAllSt.setBounds(x, y, 250, 100);

        lbAllStamp = new JLabel(sh.seporateClocks().toString());
        lbAllStamp.setBounds(x, y + 20, 250, 100);
        frame.add(lbAllSt);
        frame.add(lbAllStamp);
    }
    void SetAllTime() {
        int x = 10, y = 300;

        JLabel LbH = new JLabel("Часы: ");
        LbH.setBounds(x, y, 50, 20);
        frame.add(LbH);
        JTextField TFH = new JTextField(5);
        TFH.setBounds(x + 40, y, 40, 20);
        frame.add(TFH);

        JLabel LbM = new JLabel("Минуты: ");
        LbM.setBounds(x + 90, y, 60, 20);
        frame.add(LbM);
        JTextField TFM = new JTextField(5);
        TFM.setBounds(x + 145, y, 40, 20);
        frame.add(TFM);

        JButton BtnSetAllTime = new JButton("Установить время");
        BtnSetAllTime.setBounds(x + 20, y + 30, 150, 20);
        frame.add(BtnSetAllTime);

        BtnSetAllTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    sh.SetAllTime(Integer.parseInt(TFH.getText()), Integer.parseInt(TFM.getText()));
                    for (int i = 0; i < masRB.size(); i++)
                        masRB.get(i).setText(sh.listClocks.get(i).toString());
                    lbMaxPrice.setText(sh.getClockMax().toString());
                    sliderH.setValue(sh.listClocks.get(index).hour);
                    sliderM.setValue(sh.listClocks.get(index).minute);
                    if (sh.listClocks.get(index) instanceof ClockSec) {
                        Second.setText("0");
                        razde2.setVisible(true);
                        Second.setVisible(true);
                    }
                    else {
                        razde2.setVisible(false);
                        Second.setVisible(false);
                    }
                } catch (ThrowOutException ex) {
                    JOptionPane.showConfirmDialog(null, "Некорректный ввод", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                    TFH.setText("");
                    TFM.setText("");
                    throw new RuntimeException(ex);
                }
                TFH.setText("");
                TFM.setText("");
            }
        });
    }
    void SetTime() {
        int x = 360, y = 300;
        Description.setFont(new Font("", Font.BOLD, 14));
        Description.setBounds(x, y - 30, 100, 25);
        frame.add(Description);

        Hour.setFont(new Font("", Font.BOLD, 25));
        Hour.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
        Hour.setBounds(x, y, 33, 25);
        frame.add(Hour);

        JLabel razde1 = new JLabel(":");
        razde1.setFont(new Font("", Font.BOLD, 25));
        razde1.setBounds(x + 38, y - 2, 10, 25);
        frame.add(razde1);

        Minute.setFont(new Font("", Font.BOLD, 25));
        Minute.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
        Minute.setBounds(x + 50, y, 33, 25);
        frame.add(Minute);

        razde2.setVisible(false);
        razde2.setFont(new Font("", Font.BOLD, 25));
        razde2.setBounds(x + 88, y - 2, 10, 25);
        frame.add(razde2);

        Second.setVisible(false);
        Second.setFont(new Font("", Font.BOLD, 25));
        Second.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
        Second.setBounds(x + 100, y, 33, 25);
        frame.add(Second);
    }
    void SliderSetTime() {
        int x = 350, y = 350;
        frame.add(sliderH);
        sliderH.setBounds(x + 20, y, 150, 40);
        sliderH.setMajorTickSpacing(5);
        sliderH.setPaintTicks(true);
        sliderH.setPaintLabels(true);

        JLabel lbH = new JLabel("Часы: ");
        lbH.setBounds(x - 40, y ,100, 20);
        frame.add(lbH);

        sliderH.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = ((JSlider)e.getSource()).getValue();
                Hour.setText(String.valueOf(value));
            }
        });

        frame.add(sliderM);
        sliderM.setBounds(x + 20, y + 50, 150, 40);
        sliderM.setMajorTickSpacing(10);
        sliderM.setPaintTicks(true);
        sliderM.setPaintLabels(true);

        JLabel lbM = new JLabel("Минуты: ");
        lbM.setBounds(x - 40, y + 50,100, 20);
        frame.add(lbM);

        sliderM.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = ((JSlider)e.getSource()).getValue();
                Minute.setText(String.valueOf(value));
            }
        });

        frame.add(sliderS);
        sliderS.setBounds(x + 20, y + 100, 150, 40);
        sliderS.setMajorTickSpacing(10);
        sliderS.setPaintTicks(true);
        sliderS.setPaintLabels(true);

        lbSliderS.setBounds(x - 40, y + 100,100, 20);
        frame.add(lbSliderS);

        sliderS.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = ((JSlider)e.getSource()).getValue();
                Second.setText(String.valueOf(value));
            }
        });

        JButton BtnSetClock = new JButton("Изменить время");
        BtnSetClock.setBounds(x + 15, y + 150, 150, 20);
        frame.add(BtnSetClock);

        BtnSetClock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sh.listClocks.get(index).hour = sliderH.getValue();
                sh.listClocks.get(index).minute = sliderM.getValue();
                if (sh.listClocks.get(index) instanceof ClockSec)
                    ((ClockSec) sh.listClocks.get(index)).second = sliderS.getValue();
                masRB.get(index).setText(sh.listClocks.get(index).toString());
                lbMaxPrice.setText(sh.getClockMax().toString());
            }
        });
    }
    void AddTimeClock() throws Clock{
        int x = 600, y = 10;
        JLabel LbAddTime = new JLabel("Перевести время:");
        LbAddTime.setBounds(x, y, 150, 20);
        frame.add(LbAddTime);

        JLabel LbH = new JLabel("Часы: ");
        LbH.setBounds(x, y + 30, 70, 20);
        frame.add(LbH);

        JTextField TFAddH = new JTextField(5);
        TFAddH.setBounds(x + 70, y + 30, 28, 20);
        frame.add(TFAddH);

        JLabel LbM = new JLabel("Минуты: ");
        LbM.setBounds(x, y + 60, 70, 20);
        frame.add(LbM);

        JTextField TFAddM = new JTextField(5);
        TFAddM.setBounds(x + 70, y + 60, 28, 20);
        frame.add(TFAddM);

        LbS.setBounds(x, y + 90, 70, 20);
        frame.add(LbS);

        TFAddS.setBounds(x + 70, y + 90, 28, 20);
        frame.add(TFAddS);

        JButton BtnAddTime = new JButton("Перевести");
        BtnAddTime.setBounds(x + 15, y + 120, 150, 20);
        frame.add(BtnAddTime);

        BtnAddTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!TFAddH.getText().isEmpty()) {
                    try {
                        sh.listClocks.get(index).AddTime(Hand.HOUR, Integer.parseInt(TFAddH.getText()));
                        (masRB.get(index)).setText(String.valueOf(sh.listClocks.get(index)));
                        sliderH.setValue(sh.listClocks.get(index).hour);
                        TFAddH.setText("");
                    } catch (ThrowOutException ex) {
                        TFAddH.setText("");
                        throw new RuntimeException(ex);
                    }
                }
                if (!TFAddM.getText().isEmpty()) {
                    try {
                        sh.listClocks.get(index).AddTime(Hand.MINUTE, Integer.parseInt(TFAddM.getText()));
                        (masRB.get(index)).setText(String.valueOf(sh.listClocks.get(index)));
                        sliderH.setValue(sh.listClocks.get(index).hour);
                        sliderM.setValue(sh.listClocks.get(index).minute);
                        TFAddM.setText("");
                    } catch (ThrowOutException ex) {
                        TFAddM.setText("");
                        throw new RuntimeException(ex);
                    }
                }
                if (!TFAddS.getText().isEmpty()) {
                    try {
                        if (sh.listClocks.get(index) instanceof ClockSec) {
                            sh.listClocks.get(index).AddTime(Hand.SECOND, Integer.parseInt(TFAddS.getText()));
                            (masRB.get(index)).setText(String.valueOf(sh.listClocks.get(index)));
                            sliderH.setValue(sh.listClocks.get(index).hour);
                            sliderM.setValue(sh.listClocks.get(index).minute);
                            sliderS.setValue(((ClockSec) sh.listClocks.get(index)).second);
                            TFAddS.setText("");
                        }
                    } catch (ThrowOutException ex) {
                        TFAddS.setText("");
                        throw new RuntimeException(ex);
                    }
                }
                lbMaxPrice.setText(sh.getClockMax().toString());
            }
        });
    }

    MyFrame() {
    frame.setBounds(100, 100, 830, 600);
    frame.setTitle("Часы");
    frame.setLayout(null);
    MaxPriceClock();
    AddClock();
    MaxCounter();
    AllStamp();
    SetAllTime();
    new MyRadioButton();
    SetTime();
    SliderSetTime();
    try {
            AddTimeClock();
        } catch (Clock e) {
            throw new RuntimeException(e);
        }

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    }
}