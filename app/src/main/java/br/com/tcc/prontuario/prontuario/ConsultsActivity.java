package br.com.tcc.prontuario.prontuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consults);
        handleData();
    }

    private void handleData() {
        Bundle bundle = getIntent().getExtras();
        String consultId = bundle.getString("consult_id");

        ConsultData consultData = new ConsultData();
        consultData.setId(consultId);

        Call<ConsultData> call = new RetrofitConfig().getServices().getConsultById(consultData);
        call.enqueue(new Callback<ConsultData>() {
            @Override
            public void onResponse(Call<ConsultData> call, Response<ConsultData> response) {
                if (response.isSuccessful()) {
                    ConsultData data = response.body();
                    loadForm(data);
                }
            }

            @Override
            public void onFailure(Call<ConsultData> call, Throwable t) {
                Log.i("DEU_RUIM_CONSULTA", t.getMessage());
            }
        });
    }

    private void loadForm(ConsultData data) {
        TextView consultIdText = findViewById(R.id.consult_id_text);
        TextView medicCrmText = findViewById(R.id.consult_medic_crm_text);
        TextView medicNameText = findViewById(R.id.consult_medic_name_text);
        TextView pacientCpfText = findViewById(R.id.consult_pacient_cpf_text);
        TextView pacientNameText = findViewById(R.id.consult_pacient_name_text);
        TextView pacientHeightText = findViewById(R.id.consult_pacient_height_text);
        TextView pacientWeightText = findViewById(R.id.consult_pacient_weight_text);
        TextView pacientBloodPressureText = findViewById(R.id.consult_pacient_blood_pressure_text);
        TextView consultDateText = findViewById(R.id.consult_date_text);
        TextView pacientSymptomsText = findViewById(R.id.consult_pacient_symptoms_text);
        TextView pacientDiagnosisText = findViewById(R.id.consult_pacient_diagnosis_text);
        TextView pacientTreatmentText = findViewById(R.id.consult_pacient_treatment_text);

        consultIdText.setText(data.getId());
        medicCrmText.setText(data.getCrm());
        medicNameText.setText(data.getMedicName());
        pacientCpfText.setText(data.getCpf());
        pacientNameText.setText(data.getPacientName());
        pacientHeightText.setText(data.getPacientHeight());
        pacientWeightText.setText(data.getPacientWeight());
        pacientBloodPressureText.setText(data.getPacientBloodPressure());
        consultDateText.setText(data.getDate());
        pacientSymptomsText.setText(data.getSymptom());
        pacientDiagnosisText.setText(data.getDiagnosis());
        pacientTreatmentText.setText(data.getTreatment());
    }
}
