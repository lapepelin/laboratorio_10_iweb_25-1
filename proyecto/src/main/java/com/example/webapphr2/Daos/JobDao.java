package com.example.webapphr2.Daos;


import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobDao extends DaoBase {

    public ArrayList<Job> listarTrabajos() {

        ArrayList<Job> lista = new ArrayList<>();

        String sql = "SELECT * FROM jobs";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getString(1));
                job.setJobTitle(rs.getString(2));
                job.setMinSalary(rs.getInt(3));
                job.setMaxSalary(rs.getInt(4));
                lista.add(job);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JobDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public Job obtenerTrabajo(String jobId) {

        Job job = null;

        String sql = "SELECT * FROM jobs WHERE job_id = ?";
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, jobId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    job = new Job();
                    job.setJobId(rs.getString(1));
                    job.setJobTitle(rs.getString(2));
                    job.setMinSalary(rs.getInt(3));
                    job.setMaxSalary(rs.getInt(4));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JobDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return job;
    }

    public void crearTrabajo(String jobId, String jobTitle, int minSalary, int maxSalary) throws SQLException {

        String sql = "INSERT INTO jobs (job_id,job_title,min_salary,max_salary) "
                + "VALUES (?,?,?,?)";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, jobId);
            pstmt.setString(2, jobTitle);
            pstmt.setInt(3, minSalary);
            pstmt.setInt(4, maxSalary);
            pstmt.executeUpdate();
        }
    }

    public void actualizarTrabajo(String jobId, String jobTitle, int minSalary, int maxSalary) throws SQLException {

        String sql = "UPDATE jobs SET job_title = ?, min_salary = ?, max_salary = ? "
                + "WHERE job_id = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, jobTitle);
            pstmt.setInt(2, minSalary);
            pstmt.setInt(3, maxSalary);
            pstmt.setString(4, jobId);
            pstmt.executeUpdate();
        }
    }

    public void borrarTrabajo(String jobId) throws SQLException {

        String sql = "DELETE FROM jobs WHERE job_id = ?";
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, jobId);
            pstmt.executeUpdate();
        }
    }
}
