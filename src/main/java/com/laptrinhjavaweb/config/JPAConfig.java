package com.laptrinhjavaweb.config;

import com.laptrinhjavaweb.initialDatabase.InitialDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"com.laptrinhjavaweb.repository"}) // để sử dụng được JPARepository
@EnableTransactionManagement
public class JPAConfig {

    // đóng mở kết nói - connection EntityManagerFactory tạo ra EntityManager
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPersistenceUnitName("persistence-data");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    // Quản lý transaction - nếu đúng thì mới đưa vào SQL; Nếu sai nó sẽ k đưa vào ( kiểu dữ liệu...)
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    // EntityManager
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    // SQL information
    // local
    /*@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/appdev2");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }*/
        // MY SQL OK nhung ko them dc vao db
/*    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("frwahxxknm9kwy6c.cbetxkdyhwsb.us-east-1.rds.amazonaws.com");
        dataSource.setUrl("mysql://b98mg76kcogv7h1m:ehuhpjvm618c7p8s@frwahxxknm9kwy6c.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/lxs83cotvhclcese");
        dataSource.setUsername("b98mg76kcogv7h1m");
        dataSource.setPassword("ehuhpjvm618c7p8s");
        return dataSource;
    }*/
    // postgreSQL
/*    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5433/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("root");
        return dataSource;
    }*/
   // jdbc:postgresql://ec2-35-169-188-58.compute-1.amazonaws.com:5432/dbg6sua5v6iphl?password=2995810af148b172d7f2243f638580d9c53fc3691f458dd61bb74895f3fe5b76&sslmode=require&user=tnfopkhoxhchwv
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-35-169-188-58.compute-1.amazonaws.com:5432/dbg6sua5v6iphl?password=2995810af148b172d7f2243f638580d9c53fc3691f458dd61bb74895f3fe5b76&sslmode=require&user=tnfopkhoxhchwv");
        dataSource.setUsername("tnfopkhoxhchwv");
        dataSource.setPassword("2995810af148b172d7f2243f638580d9c53fc3691f458dd61bb74895f3fe5b76");
        return dataSource;
    }
	
    /*
     * EntityManager là một interface cung cấp các API cho việc tương tác với các Entity.
     * Persist: phương thức này dùng để lưu một thực thể mới tạo vào cơ sở dữ liệu.
     * Merge: dùng để cập nhật trạng thái của entity vào cơ sở dữ liệu.
     * Remove: xóa một instance của entity.
     */
    @Bean
    Properties additionalProperties() {
        Properties properties = new Properties();
//		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
        // Tao table moi, xóa bảng cũ
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        // run nhung k tao - xoa table cu
//        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        // tạo bảng, k xóa bảng cũ - CREATE DANG LOI
//		properties.setProperty("hibernate.hbm2dll.auto", "create");
        // update
//		properties.setProperty("hibernate.hbm2dll.auto", "update");
//        properties.setProperty("hibernate.hbm2ddl.import_files", "import_initial_data.sql"); - NOT WORKING
        // de dang nhap vao duoc, load lazy bang user_role. Can co code sau:
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        return properties;
    }
}