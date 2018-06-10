package br.com.tcc.prontuario.prontuario;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ConsultsMedicAdapter extends ArrayAdapter<ConsultMedic> {

    public ConsultsMedicAdapter(@NonNull Context context, int resource, @NonNull ConsultMedic[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.card_medical_consult, null);
        }

        ConsultMedic p = getItem(position);

        if (p != null) {
            TextView id = v.findViewById(R.id.consult_id_text_medical_card_consult);
            TextView crm = v.findViewById(R.id.crm_text_medical_card_consult);
            TextView name = v.findViewById(R.id.med_name_medical_card_consult);
            TextView date = v.findViewById(R.id.consult_date_medical_card_consult);

            if (id != null) {
                id.setText(p.getId());
            }

            if (crm != null) {
                crm.setText(p.getCrm());
            }

            if (name != null) {
                name.setText(p.getName());
            }

            if (date != null) {
                date.setText(p.getDate());
            }
        }

        return v;
    }
}
