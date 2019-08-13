/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bjsasc.licenseGenTools.ui;

import java.awt.HeadlessException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import bjsasc.licenseGenTools.entity.FunctionInfo;
import bjsasc.licenseGenTools.utils.CommonUtils.FuncListTable;
import bjsasc.licenseGenTools.utils.GetMachineInfo;

/**
 *
 * @author Test
 */
public class BuyFormalLicense extends javax.swing.JDialog {

    /**
	 *
	 */
	private DefaultTableModel model;

	private List<FunctionInfo> infoList = new ArrayList<FunctionInfo>();

	private String product;

	private String[] yearList;

	/**
	 * 表头
	 */
	private static String[] COLUMN_NAMES = {"","序号", "功能名称", "功能描述"};

	/**
	 * 表格行高
	 */
	private static final int ROW_HEIGHT = 30;

	/**
	 * 列的宽度
	 */
	private static final int[] COLUMN_WIDTH = { 50, 50, 100, 240 };

    /**
     * Creates new form BuyFormalLicense
     * @throws IOException
     * @throws UnknownHostException
     * @throws SocketException
     */
    public BuyFormalLicense(java.awt.Frame parent, boolean modal) throws SocketException, UnknownHostException, IOException {
        super(parent, modal);
        initComponents();
        // 居中显示
        this.setLocationRelativeTo(null);
        setTitle("购买正式版License");
    }
//    public BuyFormalLicense(java.awt.Frame parent, boolean modal
//    		, List<FunctionInfo> infoList, String[] yearList) throws SocketException, UnknownHostException, IOException {
	public BuyFormalLicense(java.awt.Frame parent, boolean modal
    		, String[] yearList, String product) throws SocketException, UnknownHostException, IOException {
        super(parent, modal);
        initComponents();
//        this.infoList = infoList;
        this.yearList = yearList;
        this.product = product;
		myInit();
    }

    /**
	 * 初始化
     * @throws IOException
     * @throws UnknownHostException
     * @throws SocketException
	 */
	private void myInit() throws SocketException, UnknownHostException, IOException {
		// 居中显示
        this.setLocationRelativeTo(null);
        setTitle("购买正式版License");

        codeNumber.setText(GetMachineInfo.getMachineInfo());

        // 初始化购买年限下拉列表
        yearNumber.setModel(new javax.swing.DefaultComboBoxModel(yearList));

		// 初始化表格
		initTable();
	}

	/**
	 * 初始化表格
	 */
	/*private void initTable() {
		// 获取表格内容
		Object[][] tableData = genTableData(infoList);
		model = new DefaultTableModel(tableData, COLUMN_NAMES) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				}
				return false;
			}
		};
		// 设置新的表格
		infoTable = new JTable(model);
		jScrollPane1.setViewportView(infoTable);

		// 设置行高
		infoTable.setRowHeight(ROW_HEIGHT);
		// 设置表格列宽
		for (int i = 0; i < COLUMN_WIDTH.length; i++) {
			infoTable.getColumnModel().getColumn(i)
					.setPreferredWidth(COLUMN_WIDTH[i]);
		}

		//将首列设置为复选框
		infoTable.getColumnModel().getColumn(0)
		.setCellRenderer(new TableCellRenderer(){
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				// TODO Auto-generated method stub
				JCheckBox jcb = new JCheckBox();
				jcb.setSelected(isSelected);
//				jcb.setSelected(hasFocus);
				jcb.setHorizontalAlignment((int)0.5f);
				return jcb;
			}
		});
	}*/
	private void initTable() {
        FuncListTable flt = new FuncListTable();
        /**
         * 由于MyTable类继承了AbstractTableModel，并且实现了getColmunCount()、getRowCount()、
         * getValueAt()方法 由此可以通过通过MyTable18来产生TableModel的实体
         */

        //flt.setValueAt(false, 0, 0);

        infoTable = new JTable(flt);
        JCheckBox jcb = new JCheckBox();
        infoTable.getColumnModel().getColumn(0)
                .setCellEditor(new DefaultCellEditor(jcb));
        jScrollPane1.setViewportView(infoTable);

        // 设置行高
 		infoTable.setRowHeight(ROW_HEIGHT);
 		// 设置表格列宽
 		for (int i = 0; i < COLUMN_WIDTH.length; i++) {
 			infoTable.getColumnModel().getColumn(i)
 			.setPreferredWidth(COLUMN_WIDTH[i]);
 		}
	}

	/**
	 *
	 * @param infoList
	 * @return
	 */
    /*private Object[][] genTableData(List<FunctionInfo> infoList) {
		// TODO Auto-generated method stub
    	if (infoList == null) {
			return null;
		}
    	Object[][] dataArr = new String[infoList.size()][4];
		for (int i = 0; i < infoList.size(); i++) {
			// "序号", "功能名称", "功能描述"
			FunctionInfo functionInfo = infoList.get(i);
			dataArr[i][1] = functionInfo.getId()+"";
			dataArr[i][2] = functionInfo.getName();
			dataArr[i][3] = functionInfo.getStatus();
		}
		return dataArr;
	}*/

	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        applicationCode = new javax.swing.JLabel();
        userCompany = new javax.swing.JLabel();
        contactPerson = new javax.swing.JLabel();
        contactPhone = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        codeNumber = new javax.swing.JTextField();
        companyName = new javax.swing.JTextField();
        personName = new javax.swing.JTextField();
        phoneNumber = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        years = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        yearNumber = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        functions = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("基本信息"));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 220));
        jPanel1.setLayout(new java.awt.BorderLayout());

        applicationCode.setText("申请码");
        applicationCode.setPreferredSize(new java.awt.Dimension(60, 30));

        userCompany.setText("需求单位*");
        userCompany.setPreferredSize(new java.awt.Dimension(60, 30));

        contactPerson.setText("联系人*");
        contactPerson.setPreferredSize(new java.awt.Dimension(60, 30));

        contactPhone.setText("联系方式*");
        contactPhone.setPreferredSize(new java.awt.Dimension(60, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(contactPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contactPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(applicationCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(applicationCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(userCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(contactPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(contactPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        codeNumber.setEditable(false);
        codeNumber.setPreferredSize(new java.awt.Dimension(340, 30));

        companyName.setPreferredSize(new java.awt.Dimension(340, 30));

        personName.setPreferredSize(new java.awt.Dimension(340, 30));

        phoneNumber.setPreferredSize(new java.awt.Dimension(340, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(personName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(codeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(companyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(personName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(phoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel4.setPreferredSize(new java.awt.Dimension(500, 80));

        jLabel1.setText("保存信息到软件安装目录并发送至神软888888888@bjsasc.com");
        jLabel1.setPreferredSize(new java.awt.Dimension(420, 20));

        saveButton.setText("保存");
        saveButton.setPreferredSize(new java.awt.Dimension(80, 30));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("购买信息"));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel6.setPreferredSize(new java.awt.Dimension(488, 50));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel9.setPreferredSize(new java.awt.Dimension(100, 50));

        years.setText("购买年限*");
        years.setPreferredSize(new java.awt.Dimension(60, 30));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(years, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(years, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE)))
        );

        jPanel6.add(jPanel9, java.awt.BorderLayout.LINE_START);

        yearNumber.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        yearNumber.setPreferredSize(new java.awt.Dimension(340, 30));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(yearNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(yearNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel8.setLayout(new java.awt.BorderLayout());

        functions.setText("购买功能*");
        functions.setPreferredSize(new java.awt.Dimension(60, 30));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(functions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(functions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(196, Short.MAX_VALUE)))
        );

        jPanel8.add(jPanel11, java.awt.BorderLayout.LINE_START);

        jPanel12.setPreferredSize(new java.awt.Dimension(28, 226));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel12, java.awt.BorderLayout.LINE_END);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel14.setPreferredSize(new java.awt.Dimension(20, 226));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );

        jPanel13.add(jPanel14, java.awt.BorderLayout.LINE_START);

        /*infoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));*/
        jScrollPane1.setViewportView(infoTable);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
        );

        jPanel13.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel7, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        //判断申请信息是否输入
        if((companyName.getText()!=null && companyName.getText().length()!=0)
            && (personName.getText()!="" && personName.getText().length()!=0)
            && (phoneNumber.getText()!="" && phoneNumber.getText().length()!=0)){

            //在软件运行目录下创建文本信息保存申请文档
            File file = new File(System.getProperty("user.dir")+"\\正式版license申请单.txt");
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            /*StringBuilder rows = new StringBuilder();
            for(int i=0;i<infoTable.getSelectedRows().length;i++){
            	rows.insert(i, infoTable.getSelectedRows()[i]+1);
            }
            String row = rows.toString();*/

            StringBuilder rows = new StringBuilder();
            for(int i=0; i<infoTable.getRowCount(); i++){
            	if(infoTable.getValueAt(i, 0).equals(true)){
            		rows.append(i+1);
            	}
            }
            String row = rows.toString();
            try {
                FileWriter writer = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write("产品：" + product + "\r\n");
                bufferedWriter.write("类型：正式版" + "\r\n");
                bufferedWriter.write("申请码：" + codeNumber.getText() + "\r\n");
                bufferedWriter.write("购买年限：" + yearNumber.getSelectedItem() + "\r\n");
                bufferedWriter.write("购买功能：" + row + "\r\n");
                bufferedWriter.write("需求单位：" + companyName.getText() + "\r\n");
                bufferedWriter.write("联系人：" + personName.getText() + "\r\n");
                bufferedWriter.write("联系方式：" + phoneNumber.getText());
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //关闭对话框
            this.setVisible(false);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private String getSelectedRows() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuyFormalLicense.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuyFormalLicense.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuyFormalLicense.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuyFormalLicense.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BuyFormalLicense dialog = null;
				try {
					dialog = new BuyFormalLicense(new javax.swing.JFrame(), true);
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SocketException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel applicationCode;
    private javax.swing.JTextField codeNumber;
    private javax.swing.JTextField companyName;
    private javax.swing.JLabel contactPerson;
    private javax.swing.JLabel contactPhone;
    private javax.swing.JLabel functions;
    private javax.swing.JTable infoTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField personName;
    private javax.swing.JTextField phoneNumber;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel userCompany;
    private javax.swing.JComboBox yearNumber;
    private javax.swing.JLabel years;
    // End of variables declaration//GEN-END:variables
}
