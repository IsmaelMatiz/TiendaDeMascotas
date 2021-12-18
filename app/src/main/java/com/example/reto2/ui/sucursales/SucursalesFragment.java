package com.example.reto2.ui.sucursales;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;
import com.example.reto2.FormActivity;
import com.example.reto2.R;
import com.example.reto2.adaptadores.SucursalAdapter;
import com.example.reto2.casos_uso.CasoUsoSucursal;
import com.example.reto2.databinding.FragmentSucursalesBinding;
import com.example.reto2.datos.DBHelper;
import com.example.reto2.modelos.Sucursal;
import java.util.ArrayList;

public class SucursalesFragment extends Fragment {
    private String TABLE_NAME = "SUCURSALES";
    private CasoUsoSucursal casoUsoSucursales;
    private GridView gridView;
    private DBHelper dbHelper;
    private ArrayList<Sucursal> sucursal;
    SwipeRefreshLayout refreshLayout;
    private FragmentSucursalesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSucursalesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        refreshLayout = root.findViewById(R.id.refresco);
        try{
            casoUsoSucursales = new CasoUsoSucursal();
            dbHelper = new DBHelper(getContext());
            Cursor cursor = dbHelper.getData(TABLE_NAME);
            sucursal = casoUsoSucursales.llenarListaSucursales(cursor);
            gridView = (GridView) root.findViewById(R.id.gridSucursales);
            SucursalAdapter sucursalAdapter = new SucursalAdapter(root.getContext(), sucursal);
            gridView.setAdapter(sucursalAdapter);
        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
        //refresco por wipe
        refreshLayout = root.findViewById(R.id.refrescoSucursales);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try{
                    casoUsoSucursales = new CasoUsoSucursal();
                    dbHelper = new DBHelper(getContext());
                    Cursor cursor = dbHelper.getData(TABLE_NAME);
                    sucursal = casoUsoSucursales.llenarListaSucursales(cursor);
                    gridView = (GridView) root.findViewById(R.id.gridSucursales);
                    SucursalAdapter sucursalAdapter = new SucursalAdapter(root.getContext(), sucursal);
                    gridView.setAdapter(sucursalAdapter);
                }catch (Exception e){
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

                refreshLayout.setRefreshing(false);
            }

        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(getContext(), FormActivity.class);
                intent.putExtra("name","SUCURSALES");
                getActivity().startActivity(intent);
                //Toast.makeText(getContext(), "Hola Sucursales", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemFavoritos:
                Toast.makeText(getContext(), "puedes marcar una Sucursal como favorita en la seccionde agregar, consultala, marcala como favorita y actializa", Toast.LENGTH_LONG).show();
                return true;
            case R.id.itemPregunta:
                Toast.makeText(getContext(), "Para refrescar la vista desliza hacia abajo", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}