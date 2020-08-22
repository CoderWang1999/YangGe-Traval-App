package com.itheima.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.itheima.domain.system.SysLog;
import com.itheima.mapper.system.SysLogMapper;
import com.itheima.service.system.SysLogService;
import com.itheima.utils.MapperFactory;
import com.itheima.utils.TransactionUtil;
import com.itheima.utils.UidUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class SysLogServiceImpl implements SysLogService {

   //新增日志
    @Override
    public void save(SysLog sysLog) {
        SqlSession sqlSession = MapperFactory.getSqlSession();
        SysLogMapper mapper = MapperFactory.getMapper(sqlSession, SysLogMapper.class);
        //为syslog获取一个不重复id
        String id = UidUtil.getUid();
        sysLog.setId(id);
        mapper.save(sysLog);
         //提交事务关闭资源
        TransactionUtil.commit(sqlSession);
        TransactionUtil.close(sqlSession);
    }

    @Override
    public List<SysLog> findAll(String pageNum, String pageSize) {
        SqlSession sqlSession = null;
        //为了保证遇到异常sqlSession对象也关闭 讲代码进行异常处理

        try {
            sqlSession = MapperFactory.getSqlSession();
            SysLogMapper mapper = sqlSession.getMapper(SysLogMapper.class);

            //调用pageHelper对象startPage方法
            PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
            List<SysLog> list = mapper.findAll();

            return list;
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        } finally {
           TransactionUtil.close(sqlSession);
        }
    }

    @Override
    public boolean deleteById(String ids) {
        SqlSession sqlSession = null;
        boolean flag = false;
        try {
            String[] id = ids.split(",");
            sqlSession = MapperFactory.getSqlSession();
            SysLogMapper mapper = sqlSession.getMapper(SysLogMapper.class);
            mapper.deleteById(id);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TransactionUtil.commit(sqlSession);
            TransactionUtil.close(sqlSession);
        }
        return flag;
    }

    @Override
    public ByteArrayOutputStream getReport() throws IOException {
        //查询所有
        List<SysLog> syslogList = null;
        SqlSession session = null;

        try {
            session = MapperFactory.getSqlSession();
           SysLogMapper mapper = session.getMapper(SysLogMapper.class);
            syslogList = mapper.findAll();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        //1.获取到对应的Excel文件，工作簿文件
        Workbook wb = new XSSFWorkbook();
        //2.创建工作表
        Sheet s = wb.createSheet("日志文件");

        //设置通用配置
//        s.setColumnWidth(4,100);
        CellStyle cs_field = wb.createCellStyle();
        cs_field.setAlignment(HorizontalAlignment.CENTER);
        cs_field.setBorderTop(BorderStyle.THIN);
        cs_field.setBorderBottom(BorderStyle.THIN);
        cs_field.setBorderLeft(BorderStyle.THIN);
        cs_field.setBorderRight(BorderStyle.THIN);
        s.addMergedRegion(new CellRangeAddress(1,1,1,7));

        Row row_1 = s.createRow(1);
        Cell cell_1_1 = row_1.createCell(1);
        cell_1_1.setCellValue("日志文件导出信息");

        //创建一个样式
        CellStyle cs_title = wb.createCellStyle();
        cs_title.setAlignment(HorizontalAlignment.CENTER);
        cs_title.setVerticalAlignment(VerticalAlignment.CENTER);
        cell_1_1.setCellStyle(cs_title);
        //制作表头
        String[] fields = {"操作ID","访问时间","访问用户","访问ip","资源url",
                "执行时间","访问方法"};
        Row row_2 = s.createRow(2);
        for (int i = 0; i < fields.length; i++) {
            Cell cell_2_temp = row_2.createCell(1 + i); //++
            cell_2_temp.setCellValue(fields[i]);    //++
            cell_2_temp.setCellStyle(cs_field);
        }


        int row_index = 0;
        for (SysLog q : syslogList) {
            int cell_index = 0;
            Row row_temp = s.createRow(3 + row_index++);

            Cell cell_data_1 = row_temp.createCell(1 + cell_index++);
            cell_data_1.setCellValue(q.getId());    //++
            cell_data_1.setCellStyle(cs_field);

            Cell cell_data_2 = row_temp.createCell(1 + cell_index++);
            cell_data_2.setCellValue(q.getVisitTimeStr());    //++
            cell_data_2.setCellStyle(cs_field);

            Cell cell_data_3 = row_temp.createCell(1 + cell_index++);
            cell_data_3.setCellValue(q.getUsername());    //++
            cell_data_3.setCellStyle(cs_field);

            Cell cell_data_4 = row_temp.createCell(1 + cell_index++);
            cell_data_4.setCellValue(q.getIp());    //++
            cell_data_4.setCellStyle(cs_field);

            Cell cell_data_5 = row_temp.createCell(1 + cell_index++);
            cell_data_5.setCellValue(q.getUrl());    //++
            cell_data_5.setCellStyle(cs_field);

            Cell cell_data_6 = row_temp.createCell(1 + cell_index++);
            cell_data_6.setCellValue(q.getExecutionTime()+"毫秒");    //++
            cell_data_6.setCellStyle(cs_field);

            Cell cell_data_7 = row_temp.createCell(1 + cell_index++);
            cell_data_7.setCellValue(q.getMethod());    //++
            cell_data_7.setCellStyle(cs_field);
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //将内存中的workbook数据写入到流中
        wb.write(os);
        wb.close();
        return os;
    }


    //模糊查询
    @Override
    public List<SysLog> findLikeTime(String find, String pageNum, String pageSize) {
        SqlSession sqlSession = null;
        //为了保证遇到异常sqlSession对象也关闭 讲代码进行异常处理
        List<SysLog> list = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            SysLogMapper mapper = sqlSession.getMapper(SysLogMapper.class);

            //获取string对象进行字符串操作
            StringBuilder stringBuilder = new StringBuilder(find);
            //合并模糊查询所需要的字符
            String str = new StringBuilder("%").append(find).append("%").toString();
            //调用pageHelper对象startPage方法
            PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
            list = mapper.findLikeTime(str);
            return list;
        } catch (Exception e) {
           throw new RuntimeException("查询失败");
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
}
