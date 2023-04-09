package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    //二维随机数组，随机加载图片
    int[][] arr = new int[4][4];
    //空白格的位置
    int x=0;
    int y=0;
    //图片路径
    String path = "puzzle\\image\\animal\\animal3\\";

    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    //统计步数
    int step = 0;

    //功能条目
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLogin = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("close");
    JMenuItem accountItem = new JMenuItem("QQ号:1972454587");
    JMenu changePicture = new JMenu("更换图片");
    JMenuItem prettyGirl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");




    //规定此界面为游戏主界面
    public GameJFrame(){
        //初始化界面
        initJFrame();
        //设置菜单
        initJMenubar();
        //初始化数据
        initData();
        //初始花图片
        initImage();



        //展示出来 最后写
        this.setVisible(true);
    }

    //初始化数据，随机二维数组
    public void initData(){
        int[] tempArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random random = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = random.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        for (int i = 0; i < tempArr.length; i++) {
            if(tempArr[i] == 0){
                x = i/4;
                y = i%4;
            }
            arr[i/4][i%4] = tempArr[i];

        }


    }

    //加载图片
    private void initImage() {
        //清空原有的图片
        this.getContentPane().removeAll();
        //显示胜利的图标
        if(victory()){
            JLabel win = new JLabel(new ImageIcon("puzzle\\image\\win.png"));
            win.setBounds(203,283,197,73);
            this.getContentPane().add(win);
        }

        //加载拼图
        for (int i = 0; i < 4; i++) {
            for(int j=0;j<4;j++){
                ImageIcon imageIcon = new ImageIcon(path+arr[i][j]+".jpg");
                JLabel jLabel1 = new JLabel(imageIcon);
                jLabel1.setBounds(105*j+83,105*i+143,105,105);
                //拼图加载边框
                jLabel1.setBorder(new BevelBorder(BevelBorder.LOWERED));

                this.getContentPane().add(jLabel1);

            }
        }

        //加载步数
        JLabel stepCount = new JLabel("步数："+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);
        System.out.println("步数"+step);

        //加载背景图片
        JLabel background = new JLabel(new ImageIcon("puzzle\\image\\background.png"));
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);

        this.getContentPane().repaint();

    }

    private void initJMenubar() {
        //初始化菜单
        JMenuBar jMenuBar= new JMenuBar();
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutUs = new JMenu("关于我们");

        functionJMenu.add(changePicture);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLogin);
        functionJMenu.add(closeItem);

        changePicture.add(prettyGirl);
        changePicture.add(animal);
        changePicture.add(sport);

        aboutUs.add(accountItem);

        //绑定事件
        changePicture.addActionListener(this);
        replayItem.addActionListener(this);
        reLogin.addActionListener(this);
        closeItem.addActionListener(this);
        prettyGirl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);


        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutUs);


        //给界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603,680);
        this.setTitle("李文欣不会玩拼图");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认居中的设置
        this.setLayout(null);
        //添加键盘监听事件
        this.addKeyListener(this);


    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //按下不松开
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //加载完整图片
        if(code == 65){

            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,143,420,420);
            this.getContentPane().add(all);

            JLabel background = new JLabel(new ImageIcon("puzzle\\image\\background.png"));
            background.setBounds(40,40,508,560);
            this.getContentPane().add(background);

            //刷新界面
            this.getContentPane().repaint();


        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //判断是否胜利，如果胜利，结束此方法
        if(victory()){
            return;
        }

        //左37 上38 右39 下40
        int code = e.getKeyCode();
        if(code ==37){
            System.out.println("向左移动");
            if(y==3){
                return;
            }
            arr[x][y] = arr[x][y+1];
            arr[x][y+1] = 0;
            y++;
            step++;
            //重新加载图片
            initImage();

        }else if(code == 38){
            System.out.println("向上移动");
            if(x==3){
                return;
            }
            //x,y == 空白位置
            //x+1,y 空白下面的位置
            arr[x][y] = arr[x+1][y];
            arr[x+1][y] = 0;
            x++;
            step++;
            //重新加载图片
            initImage();

        }else if(code == 39){
            System.out.println("向右移动");
            if(y==0){
                return;
            }
            arr[x][y] = arr[x][y-1];
            arr[x][y-1] = 0;
            y--;
            step++;
            //重新加载图片
            initImage();

        }else if(code == 40){
            System.out.println("向下移动");
            if(x==0){
                return;
            }
            arr[x][y] = arr[x-1][y];
            arr[x-1][y] = 0;
            x--;
            step++;
            //重新加载图片
            initImage();
        }else if (code == 65){
            initImage();
        }else if(code == 87){
            arr = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    //判断arr 和 win是否一致
    public boolean victory(){
        for (int i = 0; i < 4; i++) {
            for(int j=0;j<4;j++){
                if(arr[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if(object == replayItem){
            System.out.println("重新游戏");
            step = 0;
            initData();
            initImage();

        }else if(object == reLogin){
            System.out.println("重新登录");
            this.setVisible(false);
            new LoginJFrame();
        }else if(object == closeItem){
            System.out.println("推出");
            System.exit(0);
        }else if (object == prettyGirl){
            System.out.println("美女");
            Random random = new Random();
            int num = random.nextInt(13)+1;
            path =  "puzzle\\image\\girl\\girl"+num+"\\";
            initData();
            initImage();

        }else if (object == animal){
            System.out.println("动物");
            Random random = new Random();
            int num = random.nextInt(8)+1;
            path =  "puzzle\\image\\animal\\animal"+num+"\\";
            initData();
            initImage();

        }else if (object == sport){
            System.out.println("运动");
            Random random = new Random();
            int num = random.nextInt(10)+1;
            path =  "puzzle\\image\\sport\\sport"+num+"\\";
            initData();
            initImage();


        }

    }
}
