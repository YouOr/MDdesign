package ui.design.lf.com.mddesign.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ui.design.lf.com.mddesign.R;
import ui.design.lf.com.mddesign.activity.ClothesInfo;
import ui.design.lf.com.mddesign.adapter.ClothesAdapter;
import ui.design.lf.com.mddesign.model.Clothes;


/**
 * Created by zhouwei on 16/12/23.
 */

public class FirstFragment extends Fragment {

    public static Fragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    private Clothes[] clothes = {new Clothes("maje1", R.mipmap.maje1), new Clothes("maje2", R.mipmap.maje2), new Clothes("maje3", R.mipmap.maje3), new Clothes("maje4", R.mipmap.maje4), new Clothes("maje5", R.mipmap.maje5)
            , new Clothes("maje6", R.mipmap.maje6), new Clothes("maje7", R.mipmap.maje7), new Clothes("maje8", R.mipmap.maje8), new Clothes("maje9", R.mipmap.maje10), new Clothes("maje11", R.mipmap.maje11)
            , new Clothes("sandro1", R.mipmap.sandro1), new Clothes("sandro2", R.mipmap.sandro2), new Clothes("sandro3", R.mipmap.sandro3), new Clothes("sandro4", R.mipmap.sandro4), new Clothes("sandro5", R.mipmap.sandro5)
            , new Clothes("sandro6", R.mipmap.sandro6), new Clothes("sandro7", R.mipmap.sandro7), new Clothes("sandro8", R.mipmap.sandro8), new Clothes("sandro9", R.mipmap.sandro9), new Clothes("sandro10", R.mipmap.sandro10)
            , new Clothes("sandro11", R.mipmap.sandro11), new Clothes("sandro12", R.mipmap.sandro12), new Clothes("sandro13", R.mipmap.sandro13), new Clothes("sandro14", R.mipmap.sandro14)};
    private List<Clothes> clothesList = new ArrayList<>();
    private ClothesAdapter clothesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment_layout, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.clothes_Rv);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        clothesAdapter = new ClothesAdapter(clothesList);
        recyclerView.setAdapter(clothesAdapter);
        initClothes();
        clothesAdapter.setOnItemClickListener(new ClothesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View View, int position) {
//                Toast.makeText(getActivity(), "点击了" + clothesList.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), ClothesInfo.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void initClothes() {
        clothesList.clear();
        for (int i = 0; i < clothes.length; i++) {
            clothesList.add(clothes[i]);
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
