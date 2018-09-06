package infnet.gads.joaolfaria.phrasesgenerator.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import infnet.gads.joaolfaria.phrasesgenerator.R;
import infnet.gads.joaolfaria.phrasesgenerator.activity.DetailsUserActivity;
import infnet.gads.joaolfaria.phrasesgenerator.domain.User;

public class UserAdapter extends RecyclerView.Adapter {

    //TODO: RESOLVER PROBLEMA QUANDO VOLTA PARA A LISTA E A MESMA FICA SE DUPLICANDO.

    List<User> usuarios;

    public UserAdapter(List<User> users) {
        this.usuarios = users;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card, parent,false);
        return new UserViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = usuarios.get(position);

        UserViewHolder cvh = (UserViewHolder) holder;
        cvh.name.setText(user.getName());
        cvh.email.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView email;

        public UserViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textName);
            email = itemView.findViewById(R.id.textEmail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent detailContact = new Intent(view.getContext(), DetailsUserActivity.class);
                    User user = usuarios.get(position);

                    String putName = user.getName();
                    detailContact.putExtra("name",putName);
                    String putEmail = user.getEmail();
                    detailContact.putExtra("email", putEmail);
                    String putPassword = user.getPassword();
                    detailContact.putExtra("password", putPassword);
                    String putCPF = user.getCPF();
                    detailContact.putExtra("cpf", putCPF);

                    view.getContext().startActivity(detailContact);
                }
            });
        }
    }

}
