package br.com.tcc.prontuario.prontuario;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ConsultsPacientAdapter extends ArrayAdapter<ConsultPacient> {

    public ConsultsPacientAdapter(@NonNull Context context, int resource, @NonNull ConsultPacient[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.card_pacient_consult, null);
        }

        ConsultPacient p = getItem(position);

        if (p != null) {
            TextView id = v.findViewById(R.id.consult_id_text_pacient_card_consult);
            TextView cpf = v.findViewById(R.id.cpf_text_pacient_card_consult);
            TextView name = v.findViewById(R.id.name_pacient_card_consult);
            TextView date = v.findViewById(R.id.consult_date_pacient_card_consult);

            if (id != null) {
                id.setText(p.getId());
            }

            if (cpf != null) {
                cpf.setText(p.getCpf());
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
