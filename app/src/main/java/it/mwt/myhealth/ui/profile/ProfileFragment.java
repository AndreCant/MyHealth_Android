package it.mwt.myhealth.ui.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.mwt.myhealth.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

//    private TextView fullNameTextView;
//    private TextView phoneTextView;
//    private TextView carTextView;
//
//    private LinearLayout profileDetails;
//    private LinearLayout profileEndedRoutes;
//    private LinearLayout profileCars;
//    private LinearLayout profilePayments;
//
//    private HeaderViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Header
//        viewModel = new ViewModelProvider(requireActivity()).get(HeaderViewModel.class);
//        viewModel.setTitleLive("Profilo");
//        viewModel.setBackLive(false);
//
//        //Bind Top Personal info
//        Context ctx = view.getContext();
//        fullNameTextView = view.findViewById(R.id.full_name);
//        phoneTextView = view.findViewById(R.id.user_phone);
//        carTextView = view.findViewById(R.id.user_car);
//
//        // Fill Top Personal Info fields
//        fillTopUserInfo(ctx);
//
//        //Bind list
//        profileDetails = view.findViewById(R.id.profile_profile_details);
//        profileEndedRoutes = view.findViewById(R.id.profile_ended_routes);
//        profileCars = view.findViewById(R.id.profile_cars);
//        profilePayments = view.findViewById(R.id.profile_payments);
//
//        //Actions
//
//        // Open Edit Profile Activity
//        profileDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), ProfileDetailsActivity.class);
//                view.getContext().startActivity(intent);
//            }
//        });
//
//        // Open Ended routes Activity
//        profileEndedRoutes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), EndedRoutes.class);
//                view.getContext().startActivity(intent);
//            }
//        });
//
//        // Open Cars Activity
//        profileCars.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), CarsActivity.class);
//                view.getContext().startActivity(intent);
//            }
//        });
//
//        // Open Payments Activity
//        profilePayments.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), PaymentsActivity.class);
//                view.getContext().startActivity(intent);
//            }
//        });
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        fillTopUserInfo(getContext());
//    }
//
//    private void fillTopUserInfo(Context context){
//        Context ctx = context;
//
//        fullNameTextView.setText(Pref.getName(ctx)+" "+Pref.getSurname(ctx));
//        phoneTextView.setText(Pref.getPhone(ctx));
//        carTextView.setText(Pref.getCar(ctx));
//    }
}