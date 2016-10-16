package ca.tutortutor.univerter;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Color;
import android.graphics.PorterDuff.Mode;

import android.os.Bundle;

import android.text.Html;

import android.text.method.LinkMovementMethod;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.webkit.WebView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Univerter extends Activity
{
   private static Category[] categories;
   static
   {
      categories = new Category[]
                   {
                      new Category
                          (R.string.cat_angle, 
                           new Conversion[]
                           {
                              new Conversion
                                  (R.string.cat_angle_circles_to_deg,
                                   360),
                              new Conversion
                                  (R.string.cat_angle_circles_to_grad,
                                   400),
                              new Conversion
                                  (R.string.cat_angle_circles_to_rad,
                                   6.283185307),
                              new Conversion
                                  (R.string.cat_angle_deg_to_circles,
                                   1.0/360.0),
                              new Conversion
                                  (R.string.cat_angle_deg_to_grad,
                                   1.0/0.9),
                              new Conversion
                                  (R.string.cat_angle_deg_to_rad,
                                   0.017453293),
                              new Conversion
                                  (R.string.cat_angle_grad_to_circles,
                                   1.0/400.0),
                              new Conversion
                                  (R.string.cat_angle_grad_to_deg,
                                   0.9),
                              new Conversion
                                  (R.string.cat_angle_grad_to_rad,
                                   0.015707963),
                              new Conversion
                                  (R.string.cat_angle_rad_to_circles,
                                   0.159154943),
                              new Conversion
                                  (R.string.cat_angle_rad_to_deg,
                                   57.295779513),
                              new Conversion
                                  (R.string.cat_angle_rad_to_grad,
                                   63.661977237)
                           }),
                      new Category
                          (R.string.cat_area, 
                           new Conversion[]
                           {
                              new Conversion
                                  (R.string.cat_area_acres_to_ha,
                                   0.404685642),
                              new Conversion
                                  (R.string.cat_area_acresuss_to_ha,
                                   0.404687261),
                              new Conversion
                                  (R.string.cat_area_a_to_ha,
                                   0.01),
                              new Conversion
                                  (R.string.cat_area_ha_to_acres,
                                   2.471053815),
                              new Conversion
                                  (R.string.cat_area_ha_to_acresuss,
                                   2.47104393),
                              new Conversion
                                  (R.string.cat_area_ha_to_a,
                                   100),
                              new Conversion
                                  (R.string.cat_area_sections_to_townships,
                                   1.0/36.0),
                              new Conversion
                                  (R.string.cat_area_sq_cm_to_sq_in,
                                   0.15500031),
                              new Conversion
                                  (R.string.cat_area_sq_ft_to_sq_m,
                                   0.09290304),
                              new Conversion
                                  (R.string.cat_area_sq_in_to_sq_cm,
                                   6.4516),
                              new Conversion
                                  (R.string.cat_area_sq_in_to_sq_yd,
                                   1.0/1296.0),
                              new Conversion
                                  (R.string.cat_area_sq_km_to_sq_m,
                                   1000000),
                              new Conversion
                                  (R.string.cat_area_sq_m_to_sq_ft,
                                   10.763910417),
                              new Conversion
                                  (R.string.cat_area_sq_m_to_sq_km,
                                   0.000001),
                              new Conversion
                                  (R.string.cat_area_sq_yd_to_sq_in,
                                   1296),
                              new Conversion
                                  (R.string.cat_area_townships_to_sections,
                                   36)
                           }),
                      new Category
                          (R.string.cat_capvol,
                           new Conversion[]
                           {
                              new Conversion
                                  (R.string.cat_capvol_galuk_to_l,
                                   4.54609),
                              new Conversion
                                  (R.string.cat_capvol_galus_to_l,
                                   3.785411784),
                              new Conversion
                                  (R.string.cat_capvol_l_to_galuk,
                                   0.219969248),
                              new Conversion
                                  (R.string.cat_capvol_l_to_galus,
                                   0.264172052),
                              new Conversion
                                  (R.string.cat_capvol_l_to_ptuk,
                                   1.759753986),
                              new Conversion
                                  (R.string.cat_capvol_l_to_ptus,
                                   2.113376419),
                              new Conversion
                                  (R.string.cat_capvol_l_to_qtuk,
                                   0.879876993),
                              new Conversion
                                  (R.string.cat_capvol_l_to_qtus,
                                   1.056688209),
                              new Conversion
                                  (R.string.cat_capvol_ptuk_to_l,
                                   0.56826125),
                              new Conversion
                                  (R.string.cat_capvol_ptus_to_l,
                                   0.473176473),
                              new Conversion
                                  (R.string.cat_capvol_qtuk_to_l,
                                   1.1365225),
                              new Conversion
                                  (R.string.cat_capvol_qtus_to_l,
                                   0.946352946),
                              new Conversion
                                  (R.string.cat_capvol_tbspm_to_tbspuk,
                                   0.844681913),
                              new Conversion
                                  (R.string.cat_capvol_tbspm_to_tbspus,
                                   1.014420681),
                              new Conversion
                                  (R.string.cat_capvol_tbspm_to_tspm,
                                   3),
                              new Conversion
                                  (R.string.cat_capvol_tbspuk_to_tbspm,
                                   1.183877604),
                              new Conversion
                                  (R.string.cat_capvol_tbspuk_to_tbspus,
                                   1.200949926),
                              new Conversion
                                  (R.string.cat_capvol_tbspuk_to_tspm,
                                   3.551632812),
                              new Conversion
                                  (R.string.cat_capvol_tbspus_to_tbspm,
                                   0.985784319),
                              new Conversion
                                  (R.string.cat_capvol_tbspus_to_tbspuk,
                                   0.832674185),
                              new Conversion
                                  (R.string.cat_capvol_tbspus_to_tspm,
                                   2.957352956),
                              new Conversion
                                  (R.string.cat_capvol_tspm_to_tbspm,
                                   1.0/3.0),
                              new Conversion
                                  (R.string.cat_capvol_tspm_to_tbspuk,
                                   0.281560638),
                              new Conversion
                                  (R.string.cat_capvol_tspm_to_tbspus,
                                   0.338140227),
                              new Conversion
                                  (R.string.cat_capvol_tspm_to_tspuk,
                                   0.844681913),
                              new Conversion
                                  (R.string.cat_capvol_tspm_to_tspus,
                                   1.014420681),
                              new Conversion
                                  (R.string.cat_capvol_tspuk_to_tspm,
                                   1.183877604),
                              new Conversion
                                  (R.string.cat_capvol_tspuk_to_tspus,
                                   1.200949926),
                              new Conversion
                                  (R.string.cat_capvol_tspus_to_tspm,
                                   0.985784319),
                              new Conversion
                                  (R.string.cat_capvol_tspus_to_tspuk,
                                   0.832674185)
                           }),
                      new Category
                          (R.string.cat_datastor, 
                           new Conversion[]
                           {
                              new Conversion
                                  (R.string.cat_datastor_B_to_kB,
                                   1.0/1024.0),
                              new Conversion
                                  (R.string.cat_datastor_EB_to_GB,
                                   1073741824),
                              new Conversion
                                  (R.string.cat_datastor_EB_to_TB,
                                   1048576),
                              new Conversion
                                  (R.string.cat_datastor_GB_to_EB,
                                   1.0/1073741824.0),
                              new Conversion
                                  (R.string.cat_datastor_GB_to_kB,
                                   1048576),
                              new Conversion
                                  (R.string.cat_datastor_GB_to_MB,
                                   1024),
                              new Conversion
                                  (R.string.cat_datastor_GB_to_PB,
                                   1.0/1048576.0),
                              new Conversion
                                  (R.string.cat_datastor_kB_to_B,
                                   1024),
                              new Conversion
                                  (R.string.cat_datastor_kB_to_GB,
                                   1.0/1048576.0),
                              new Conversion
                                  (R.string.cat_datastor_kB_to_MB,
                                   1.0/1024.0),
                              new Conversion
                                  (R.string.cat_datastor_MB_to_GB,
                                   1.0/1024.0),
                              new Conversion
                                  (R.string.cat_datastor_MB_to_kB,
                                   1024.0),
                              new Conversion
                                  (R.string.cat_datastor_PB_to_GB,
                                   1048576),
                              new Conversion
                                  (R.string.cat_datastor_TB_to_EB,
                                   1.0/1048576.0),
                           }),
                      new Category
                          (R.string.cat_distlen,
                           new Conversion[]
                           {
                              new Conversion
                                  (R.string.cat_distlen_a_to_u,
                                   0.0001),
                              new Conversion
                                  (R.string.cat_distlen_au_to_ly,
                                   0.000015813),
                              new Conversion
                                  (R.string.cat_distlen_cm_to_in,
                                   0.393700787),
                              new Conversion
                                  (R.string.cat_distlen_ft_to_m,
                                   0.3048),
                              new Conversion
                                  (R.string.cat_distlen_in_to_cm,
                                   2.54),
                              new Conversion
                                  (R.string.cat_distlen_in_to_twip,
                                   1440),
                              new Conversion
                                  (R.string.cat_distlen_km_to_miint,
                                   0.621371192),
                              new Conversion
                                  (R.string.cat_distlen_km_to_miusss,
                                   0.621369949),
                              new Conversion
                                  (R.string.cat_distlen_ly_to_au,
                                   63241.077088066),
                              new Conversion
                                  (R.string.cat_distlen_m_to_ft,
                                   3.280839895),
                              new Conversion
                                  (R.string.cat_distlen_m_to_yd,
                                   1.093613298),
                              new Conversion
                                  (R.string.cat_distlen_u_to_A,
                                   10000),
                              new Conversion
                                  (R.string.cat_distlen_miint_to_km,
                                   1.609344),
                              new Conversion
                                  (R.string.cat_distlen_miusss_to_km,
                                   1.609347219),
                              new Conversion
                                  (R.string.cat_distlen_picas_to_points,
                                   12),
                              new Conversion
                                  (R.string.cat_distlen_points_to_picas,
                                   1.0/12.0),
                              new Conversion
                                  (R.string.cat_distlen_twip_to_in,
                                   1.0/1440.0),
                              new Conversion
                                  (R.string.cat_distlen_yd_to_m,
                                   0.9144)
                           }),
                      new Category
                          (R.string.cat_energy,
                           new Conversion[]
                           {
                              new Conversion
                                  (R.string.cat_energy_btuit_to_calit,
                                   251.995761111),
                              new Conversion
                                  (R.string.cat_energy_calit_to_btuit,
                                   0.003968321),
                              new Conversion
                                  (R.string.cat_energy_erg_to_J,
                                   1.0/10000000.0),
                              new Conversion
                                  (R.string.cat_energy_hph_to_Wh,
                                   745.699871357),
                              new Conversion
                                  (R.string.cat_energy_hpmh_to_Wh,
                                   735.49875),
                              new Conversion
                                  (R.string.cat_energy_J_to_erg,
                                   10000000),
                              new Conversion
                                  (R.string.cat_energy_J_to_Wh,
                                   1.0/3600.0),
                              new Conversion
                                  (R.string.cat_energy_Wh_to_hph,
                                   0.001341022),
                              new Conversion
                                  (R.string.cat_energy_Wh_to_hpmh,
                                   0.001359622),
                              new Conversion
                                  (R.string.cat_energy_Wh_to_J,
                                   3600)
                           }),
                      new Category
                          (R.string.cat_force, 
                           new Conversion[]
                           {
                              new Conversion
                                  (R.string.cat_force_dyn_to_N,
                                   1.0/100000.0),
                              new Conversion
                                  (R.string.cat_force_kgf_to_kipf,
                                   0.002204623),
                              new Conversion
                                  (R.string.cat_force_kipf_to_kgf,
                                   453.592369999),
                              new Conversion
                                  (R.string.cat_force_N_to_dyn,
                                   100000),
                              new Conversion
                                  (R.string.cat_force_N_to_p,
                                   101.971621298),
                              new Conversion
                                  (R.string.cat_force_N_to_pdl,
                                   7.233013851),
                              new Conversion
                                  (R.string.cat_force_p_to_N,
                                   0.00980665),
                              new Conversion
                                  (R.string.cat_force_pdl_to_N,
                                   0.138254954)
                           }),
                      new Category
                          (R.string.cat_fuelcons, 
                           new Conversion[]
                           {
                              new Conversion
                                  (R.string.cat_fuelcons_kmpl_to_mpguk,
                                   2.824809363),
                              new Conversion
                                  (R.string.cat_fuelcons_kmpl_to_mpgus,
                                   2.352145833),
                              new Conversion
                                  (R.string.cat_fuelcons_mpguk_to_kmpl,
                                   0.35400619),
                              new Conversion
                                  (R.string.cat_fuelcons_mpgus_to_kmpl,
                                   0.425143707),
                           }),
                      new Category
                          (R.string.cat_pressure, 
                           new Conversion[]
                           {
                              new Conversion
                                  (R.string.cat_pressure_atm_to_at,
                                   1.033227453),
                              new Conversion
                                  (R.string.cat_pressure_at_to_atm,
                                   0.967841105),
                              new Conversion
                                  (R.string.cat_pressure_bars_to_pa,
                                   100000),
                              new Conversion
                                  (R.string.cat_pressure_bars_to_psi,
                                   14.503773773),
                              new Conversion
                                  (R.string.cat_pressure_bars_to_torrs,
                                   750.061682704),
                              new Conversion
                                  (R.string.cat_pressure_dynsqcm_to_kgfsqcm,
                                   0.00000102),
                              new Conversion
                                  (R.string.cat_pressure_dynsqcm_to_Nsqm,
                                   1.0/10),
                              new Conversion
                                  (R.string.cat_pressure_kgfsqcm_to_dynsqcm,
                                   980665),
                              new Conversion
                                  (R.string.cat_pressure_kgfsqcm_to_kipfsqin,
                                   0.014223343),
                              new Conversion
                                  (R.string.cat_pressure_kipfsqin_to_kgfsqcm,
                                   70.306957964),
                              new Conversion
                                  (R.string.cat_pressure_Nsqm_to_dynsqcm,
                                   10.0),
                              new Conversion
                                  (R.string.cat_pressure_pa_to_bars,
                                   1.0/100000.0),
                              new Conversion
                                  (R.string.cat_pressure_pa_to_psi,
                                   0.000145038),
                              new Conversion
                                  (R.string.cat_pressure_pa_to_torrs,
                                   0.007500617),
                              new Conversion
                                  (R.string.cat_pressure_psi_to_bars,
                                   0.068947573),
                              new Conversion
                                  (R.string.cat_pressure_psi_to_pa,
                                   6894.757293178),
                              new Conversion
                                  (R.string.cat_pressure_psi_to_torrs,
                                   51.714932572),
                              new Conversion
                                  (R.string.cat_pressure_torrs_to_bars,
                                   0.001333224),
                              new Conversion
                                  (R.string.cat_pressure_torrs_to_pa,
                                   133.322368421),
                              new Conversion
                                  (R.string.cat_pressure_torrs_to_psi,
                                   0.019336775)
                           }),
                      new Category
                          (R.string.cat_temp,
                           new Conversion[]
                           {
                              new Conversion
                                  (R.string.cat_temp_celsius_to_fahrenheit,
                                   new Converter()
                                   {
                                      @Override
                                      public double convert(Context ctx, 
                                                            double value)
                                      {
                                         if (value < -273.15)
                                         {
                                            String s;
                                            s = ctx.
                                                getString(R.string.
                                                          error_less_than_abs0);
                                            throw 
                                              new IllegalArgumentException(s);
                                         }
                                         return value*9.0/5.0+32;
                                      }
                                   },
                                   true),
                              new Conversion
                                  (R.string.cat_temp_celsius_to_kelvin,
                                   new Converter()
                                   {
                                      @Override
                                      public double convert(Context ctx, 
                                                            double value)
                                      {
                                         if (value < -273.15)
                                         {
                                            String s;
                                            s = ctx.
                                                getString(R.string.
                                                          error_less_than_abs0);
                                            throw 
                                              new IllegalArgumentException(s);
                                         }
                                         return value+273.15;
                                      }
                                   },
                                   true),
                              new Conversion
                                  (R.string.cat_temp_fahrenheit_to_celsius,
                                   new Converter()
                                   {
                                      @Override
                                      public double convert(Context ctx, 
                                                            double value)
                                      {
                                         if (value < -459.67)
                                         {
                                            String s;
                                            s = ctx.
                                                getString(R.string.
                                                          error_less_than_abs0);
                                            throw 
                                              new IllegalArgumentException(s);
                                         }
                                         return (value-32)*5.0/9.0;
                                      }
                                   },
                                   true),
                              new Conversion
                                  (R.string.cat_temp_kelvin_to_celsius,
                                   new Converter()
                                   {
                                      @Override
                                      public double convert(Context ctx, 
                                                            double value)
                                      {
                                         return value-273.15;
                                      }
                                   },
                                   false)
                           }),
                      new Category
                          (R.string.cat_time,
                           new Conversion[]
                           {
                              new Conversion
                                  (R.string.cat_time_d_to_h,
                                   24),
                              new Conversion
                                  (R.string.cat_time_d_to_m,
                                   1440),
                              new Conversion
                                  (R.string.cat_time_d_to_s,
                                   86400),
                              new Conversion
                                  (R.string.cat_time_d_to_w,
                                   1.0/7.0),
                              new Conversion
                                  (R.string.cat_time_h_to_d,
                                   1.0/24.0),
                              new Conversion
                                  (R.string.cat_time_h_to_m,
                                   60),
                              new Conversion
                                  (R.string.cat_time_h_to_s,
                                   3600),
                              new Conversion
                                  (R.string.cat_time_h_to_w,
                                   1.0/168.0),
                              new Conversion
                                  (R.string.cat_time_m_to_d,
                                   1.0/1440.0),
                              new Conversion
                                  (R.string.cat_time_m_to_h,
                                   1.0/60.0),
                              new Conversion
                                  (R.string.cat_time_m_to_s,
                                   60),
                              new Conversion
                                  (R.string.cat_time_m_to_w,
                                   1.0/10080.0),
                              new Conversion
                                  (R.string.cat_time_mo_to_w,
                                   4.345238095),
                              new Conversion
                                  (R.string.cat_time_s_to_d,
                                   1.0/86400.0),
                              new Conversion
                                  (R.string.cat_time_s_to_h,
                                   1.0/3600.0),
                              new Conversion
                                  (R.string.cat_time_s_to_m,
                                   1.0/60.0),
                              new Conversion
                                  (R.string.cat_time_s_to_w,
                                   1.0/604800.0),
                              new Conversion
                                  (R.string.cat_time_w_to_d,
                                   7),
                              new Conversion
                                  (R.string.cat_time_w_to_h,
                                   168),
                              new Conversion
                                  (R.string.cat_time_w_to_m,
                                   10080),
                              new Conversion
                                  (R.string.cat_time_w_to_mo,
                                   0.230136986),
                              new Conversion
                                  (R.string.cat_time_w_to_s,
                                   604800)
                           }),
                      new Category
                          (R.string.cat_velocity,
                           new Conversion[]
                           {
                              new Conversion
                                  (R.string.cat_velocity_fps_to_mps,
                                   0.3048),
                              new Conversion
                                  (R.string.cat_velocity_kmph_to_machsi,
                                   0.000941472),
                              new Conversion
                                  (R.string.cat_velocity_kmph_to_miph,
                                   0.621371192),
                              new Conversion
                                  (R.string.cat_velocity_machsi_to_kmph,
                                   1062.167040001),
                              new Conversion
                                  (R.string.cat_velocity_machsi_to_miph,
                                   660.000000001),
                              new Conversion
                                  (R.string.cat_velocity_mps_to_fps,
                                   3.280839895),
                              new Conversion
                                  (R.string.cat_velocity_miph_to_kmph,
                                   1.609344),
                              new Conversion
                                  (R.string.cat_velocity_miph_to_machsi,
                                   0.001515152)
                           }),
                      new Category
                          (R.string.cat_weightmass,
                           new Conversion[]
                           {
                              new Conversion
                                  (R.string.cat_weightmass_ct_to_lb,
                                   0.000440925),
                              new Conversion
                                  (R.string.cat_weightmass_ct_to_lbt,
                                   0.000535846),
                              new Conversion
                                  (R.string.cat_weightmass_emrest_to_nm,
                                   0.000543867),
                              new Conversion
                                  (R.string.cat_weightmass_gr_to_slugs,
                                   0.00000444),
                              new Conversion
                                  (R.string.cat_weightmass_g_to_kg,
                                   1.0/1000.0),
                              new Conversion
                                  (R.string.cat_weightmass_hundredweightuk_to_lb,
                                   112),
                              new Conversion
                                  (R.string.cat_weightmass_hundredweightus_to_lb,
                                   100),
                              new Conversion
                                  (R.string.cat_weightmass_kg_to_g,
                                   1000),
                              new Conversion
                                  (R.string.cat_weightmass_kg_to_lb,
                                   2.204622622),
                              new Conversion
                                  (R.string.cat_weightmass_kg_to_t,
                                   1.0/1000.0),
                              new Conversion
                                  (R.string.cat_weightmass_nm_to_emrest,
                                   1838.683660663),
                              new Conversion
                                  (R.string.cat_weightmass_nm_to_pm,
                                   1.001378374),
                              new Conversion
                                  (R.string.cat_weightmass_oz_to_lb,
                                   1.0/16.0),
                              new Conversion
                                  (R.string.cat_weightmass_oz_to_lbt,
                                   0.075954861),
                              new Conversion
                                  (R.string.cat_weightmass_ozt_to_lb,
                                   0.068571429),
                              new Conversion
                                  (R.string.cat_weightmass_ozt_to_lbt,
                                   1.0/12.0),
                              new Conversion
                                  (R.string.cat_weightmass_lb_to_ct,
                                   2267.96185),
                              new Conversion
                                  (R.string.cat_weightmass_lb_to_hundredweightuk,
                                   1.0/112.0),
                              new Conversion
                                  (R.string.cat_weightmass_lb_to_hundredweightus,
                                   1.0/100.0),
                              new Conversion
                                  (R.string.cat_weightmass_lb_to_kg,
                                   0.45359237),
                              new Conversion
                                  (R.string.cat_weightmass_lb_to_oz,
                                   16),
                              new Conversion
                                  (R.string.cat_weightmass_lb_to_ozt,
                                   14.583333333),
                              new Conversion
                                  (R.string.cat_weightmass_lb_to_stonesuk,
                                   1.0/14.0),
                              new Conversion
                                  (R.string.cat_weightmass_lb_to_stonesus,
                                   1.0/12.5),
                              new Conversion
                                  (R.string.cat_weightmass_lb_to_t,
                                   0.000453592),
                              new Conversion
                                  (R.string.cat_weightmass_lbt_to_ct,
                                   1866.208608),
                              new Conversion
                                  (R.string.cat_weightmass_lbt_to_oz,
                                   13.165714286),
                              new Conversion
                                  (R.string.cat_weightmass_lbt_to_ozt,
                                   12),
                              new Conversion
                                  (R.string.cat_weightmass_pm_to_nm,
                                   0.998623523),
                              new Conversion
                                  (R.string.cat_weightmass_slugs_to_gr,
                                   225218.33989438),
                              new Conversion
                                  (R.string.cat_weightmass_stonesuk_to_lb,
                                   14),
                              new Conversion
                                  (R.string.cat_weightmass_stonesus_to_lb,
                                   12.5),
                              new Conversion
                                  (R.string.cat_weightmass_t_to_kg,
                                   1000),
                              new Conversion
                                  (R.string.cat_weightmass_t_to_lb,
                                   2204.622621849)
                           })
                   };
   }

   private String[] catNames;
   private int curCat, curCon;

   private StringBuilder buffer;
   private int state;
   private int nDigits;
   private boolean isDecimal;
   private boolean btnCvtClicked;

   private Button btnPm;
   private EditText etDisplay;
   private DialogInterface.OnClickListener oclCat, oclCatClose;
   private DialogInterface.OnClickListener oclCon, oclConClose;
   private int choice;

   private String helpText;

   public void doCatClicked(View view)
   {
      choice = curCat;
      if (oclCat == null)
         oclCat = new DialogInterface.OnClickListener()
                  {
                     @Override
                     public void onClick(DialogInterface dialog, int which)
                     {
                        choice = which;
                     }
                  };
      if (oclCatClose == null)
         oclCatClose = new DialogInterface.OnClickListener()
                       {
                          @Override
                          public void onClick(DialogInterface dialog, int which)
                          {
                             curCat = choice;
                             curCon = 0;
                             updateConversionTitle();
                             reset();
                             btnPm.setEnabled(categories[curCat].
                                   getConversion(curCon).
                                   canBeNegative());
                          }
                       };
      new AlertDialog.Builder(Univerter.this).
          setSingleChoiceItems(catNames, curCat, oclCat).
          setTitle(R.string.categories).
          setNeutralButton(R.string.btnClose, oclCatClose).
          show();
   }

   public void doClrClicked(View view)
   {
      reset();
   }

   public void doConClicked(View view)
   {
      choice = curCon;
      if (oclCon == null)
         oclCon = new DialogInterface.OnClickListener()
                  {
                     @Override
                     public void onClick(DialogInterface dialog, int which)
                     {
                        choice = which;
                     }
                  };
      if (oclConClose == null)
         oclConClose = new DialogInterface.OnClickListener()
                       {
                          @Override
                          public void onClick(DialogInterface dialog, int which)
                          {
                             curCon = choice;
                             updateConversionTitle();
                             reset();
                             btnPm.setEnabled(categories[curCat].
                                   getConversion(curCon).
                                   canBeNegative());
                          }
                       };
      ListAdapter adapter;
      adapter = new ArrayAdapter<String>(Univerter.this, 
                                         R.layout.list_row, 
                                         categories[curCat].
                                         getConversionNames(Univerter.this));
      new AlertDialog.Builder(Univerter.this).
          setSingleChoiceItems(adapter, curCon, oclCon).
          setTitle(categories[curCat].getName(Univerter.this)).
          setNeutralButton(R.string.btnClose, oclConClose).
          show();
   }

   public void doCvtClicked(View view)
   {
      try
      {
         double value = Double.parseDouble(buffer.length() == 0 ? "0" : 
                                           buffer.toString());
         value = categories[curCat].getConversion(curCon).getConverter().
                 convert(Univerter.this, value);
         if (Math.abs(value) > 1.0e+18)
            throw new NumberFormatException(getString(R.string.overflow));
         else
         if (value != 0.0 && Math.abs(value) < 1.0e-8)
            throw new NumberFormatException(getString(R.string.underflow));
         buffer.setLength(0);
         buffer.append(""+value);
         etDisplay.setText(String.format("%,.8f", value));
      }
      catch (IllegalArgumentException iae)
      {
         Toast t = Toast.makeText(Univerter.this, iae.getMessage(), 
                                  Toast.LENGTH_SHORT);
         t.setGravity(Gravity.CENTER_HORIZONTAL|
                      Gravity.CENTER_VERTICAL, 0, 0);
         t.show();
      }
      btnCvtClicked = true;
   }

   public void doDigitClicked(View view)
   {
      if (btnCvtClicked)
      {
         reset();
         btnCvtClicked = false;
      }
      buildNumber(((String) view.getTag()).charAt(0));
      if (buffer.length() == 0)
         etDisplay.setText("0.");
      else
         etDisplay.setText(buffer.toString()+
                          (buffer.indexOf(".")==-1?".":""));
   }

   public void doDotClicked(View view)
   {
      if (btnCvtClicked)
      {
         reset();
         btnCvtClicked = false;
      }
      buildNumber('.');
   }

   public void doPmClicked(View view)
   {
      buildNumber('-');
      if (state == 1)
         etDisplay.setText(buffer.toString()+
                          (buffer.indexOf(".")==-1?".":""));
   }

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);

      catNames = new String[categories.length];
      for (int i = 0; i < catNames.length; i++)
         catNames[i] = categories[i].getName(Univerter.this);

      if (savedInstanceState == null)
      {
         curCat = 0;
         curCon = 0;
         buffer = new StringBuilder();
         state = 0;
         nDigits = 0;
         isDecimal = false;
         btnCvtClicked = false;
      }
      else
      {
         curCat = savedInstanceState.getInt("curCat");
         curCon = savedInstanceState.getInt("curCon");
         buffer = new StringBuilder(savedInstanceState.getString("buffer"));
         state = savedInstanceState.getInt("state");
         nDigits = savedInstanceState.getInt("nDigits");
         isDecimal = savedInstanceState.getBoolean("isDecimal");
         btnCvtClicked = savedInstanceState.getBoolean("btnCvtClicked");
      }

      boolean isLeftIconSupported = 
         requestWindowFeature(Window.FEATURE_LEFT_ICON);
      setContentView(R.layout.main);
      if (isLeftIconSupported)
         setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
                                    R.drawable.ic_launcher);

      updateConversionTitle();

      etDisplay = (EditText) findViewById(R.id.display);

      int[] btnDigitIds = 
      {
         R.id.btn7,
         R.id.btn8,
         R.id.btn9,
         R.id.btnClr,
         R.id.btn4,
         R.id.btn5,
         R.id.btn6,
         R.id.btnCat,
         R.id.btn1,
         R.id.btn2,
         R.id.btn3,
         R.id.btnCon,
         R.id.btn0,
         R.id.btnDot,
         R.id.btnPm,
         R.id.btnCvt
      };
      for (int i = 0; i < btnDigitIds.length; i++)
      {
         Button btn = (Button) findViewById(btnDigitIds[i]);
         if (btnDigitIds[i] == R.id.btnPm)
         {
            btnPm = btn;
            btnPm.setEnabled(categories[curCat].getConversion(curCon).
                             canBeNegative());
         }
         btn.getBackground().
             setColorFilter(Color.GRAY, Mode.MULTIPLY);
      }

      helpText = getString(R.string.help);
      int colorHelpHiliteText = getResources().
                                getColor(R.color.helpHiliteText)&0x00ffffff;
      helpText = helpText.replaceAll("#helpHiliteText", 
                                     "#"+toHexString(colorHelpHiliteText, 6));
      int colorHelpText = getResources().getColor(R.color.helpText)&0x00ffffff;
      helpText = helpText.replaceAll("#helpText",
                                     "#"+toHexString(colorHelpText, 6));
      int colorLink = getResources().getColor(R.color.link)&0x00ffffff;
      helpText = helpText.replaceAll("#link", 
                                     "#"+toHexString(colorLink, 6));
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) 
   {
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.univerter, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) 
   {
      LayoutInflater inflater;

      switch (item.getItemId()) 
      {
         case R.id.menu_help:
              inflater = (LayoutInflater) this.
                         getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              WebView wv = (WebView) inflater.inflate(R.layout.help, null);
              wv.setBackgroundColor(Color.TRANSPARENT);
              wv.loadData(helpText, "text/html", "utf-8");
              new AlertDialog.Builder(Univerter.this).
                  setView(wv).
                  setNeutralButton(R.string.btnClose, null).
                  show();
              return true;

         case R.id.menu_info:
              inflater = (LayoutInflater) this.
                         getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              View view = inflater.inflate(R.layout.info, null);
              TextView tv;
              tv = (TextView) ((ViewGroup) view).findViewById(R.id.text1);
              tv.setText(Html.fromHtml(getString(R.string.info1)));
              tv = (TextView) ((ViewGroup) view).findViewById(R.id.text2);
              tv.setText(R.string.info2);
              tv = (TextView) ((ViewGroup) view).findViewById(R.id.text3);
              tv.setText(Html.fromHtml(getString(R.string.info3)));
              tv.setMovementMethod(LinkMovementMethod.getInstance());
              tv = (TextView) ((ViewGroup) view).findViewById(R.id.text4);
              tv.setText(Html.fromHtml(getString(R.string.info4)));
              tv.setMovementMethod(LinkMovementMethod.getInstance());
              ImageView iv;
              iv = (ImageView) ((ViewGroup) view).findViewById(R.id.image);
              iv.setImageResource(R.drawable.ic_launcher);
              new AlertDialog.Builder(Univerter.this).
                  setView(view).
                  setNeutralButton(R.string.btnClose, null).
                  show();
              return true;

         default:
              return super.onOptionsItemSelected(item);
      }
   }

   @Override
   public void onSaveInstanceState(Bundle outState)
   {
      super.onSaveInstanceState(outState);
      outState.putInt("curCat", curCat);
      outState.putInt("curCon", curCon);
      outState.putString("buffer", buffer.toString());
      outState.putInt("state", state);
      outState.putInt("nDigits", nDigits);
      outState.putBoolean("isDecimal", isDecimal);
      outState.putBoolean("btnCvtClicked", btnCvtClicked);
   }

   private void buildNumber(char ch)
   {
      switch (state)
      {
         case 0: if (ch >= '1' && ch <= '9')
                 {
                    buffer.append(ch);
                    nDigits = 1;
                    state = 1;
                 }
                 else
                 if (ch == '.')
                 {
                    isDecimal = true;
                    buffer.append("0.");
                    nDigits = 1;
                    state = 1;
                 }
                 break;

         case 1: if (ch >= '0' && ch <= '9')
                 {
                    if (nDigits != 10)
                    {
                       buffer.append(ch);
                       nDigits++;
                    }
                 }
                 else
                 if (ch == '.')
                 {
                    if (isDecimal)
                       break;
                    isDecimal = true;
                    buffer.append('.');
                 }
                 else
                 if (categories[curCat].getConversion(curCon).canBeNegative() &&
                     ch == '-')
                 {
                    if (buffer.charAt(0) == '-')
                       buffer.deleteCharAt(0);
                    else
                       buffer.insert(0, '-');
                 }
      }
   }

   private void reset()
   {
      buffer.setLength(0);
      state = 0;
      nDigits = 0;
      isDecimal = false;
      etDisplay.setText("0.");
   }

   private String toHexString(int i, int numNibbles)
   {
      StringBuilder sb = new StringBuilder(Integer.toHexString(i));
      if (sb.length() > numNibbles)
         return null; // cannot fit result into numNibbles columns

      int numLeadingZeros = numNibbles-sb.length();
      for (int j = 0; j < numLeadingZeros; j++)
         sb.insert(0, '0');
      return sb.toString();
   }

   private void updateConversionTitle()
   {
      TextView tv = (TextView) findViewById(R.id.conversion1);
      if (tv != null)
      {
         String s = categories[curCat].getConversion(curCon).
                    getName(Univerter.this);
         tv.setText(s.substring(0, s.indexOf(">")-1));
         tv = (TextView) findViewById(R.id.conversion2);
         tv.setText(s.substring(s.indexOf(">")+2));
      }
      else
         setTitle(getString(R.string.app_name)+": "+
                  categories[curCat].getConversion(curCon).
                  getName(Univerter.this));
   }
}