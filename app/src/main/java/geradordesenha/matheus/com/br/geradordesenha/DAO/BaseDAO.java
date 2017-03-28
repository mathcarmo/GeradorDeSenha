package geradordesenha.matheus.com.br.geradordesenha.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import geradordesenha.matheus.com.br.geradordesenha.DB.DataBaseHelper;
import geradordesenha.matheus.com.br.geradordesenha.Entity.EntityPersist;


public abstract class BaseDAO<T extends EntityPersist> {
    protected SQLiteDatabase dataBase = null;

    public BaseDAO(Context context) {
        DataBaseHelper persistenceHelper = DataBaseHelper.getInstance(context);
        dataBase = persistenceHelper.getWritableDatabase();
    }

    public abstract String getNomeColunaPrimaryKey();

    public abstract String getNomeTabela();

    public abstract ContentValues entidadeParacontentValues(T entidade);

    public abstract T contentValuesParaEntidade(ContentValues contentValues);

    public void salvar(T entidade) {
        ContentValues values = entidadeParacontentValues(entidade);
        dataBase.insert(getNomeTabela(), null, values);
    }

    public void deletar(T t) {
        String[] valoresParaSubstituir = {
                String.valueOf(t.getId())
        };
        dataBase.delete(getNomeTabela(), getNomeColunaPrimaryKey() + " =  ?", valoresParaSubstituir);
    }

    public void deletarTodos() {
        dataBase.execSQL("DELETE FROM " + getNomeTabela());
    }

    public void editar(T t) {
        ContentValues valores = entidadeParacontentValues(t);

        String[] valoresParaSubstituir = {
                String.valueOf(t.getId())
        };
        dataBase.update(getNomeTabela(), valores, getNomeColunaPrimaryKey() + " = ?", valoresParaSubstituir);
    }

    public List<T> recuperarTodos() {
        String queryReturnAll = "SELECT * FROM " + getNomeTabela();
        List<T> result = recuperarPorQuery(queryReturnAll);
        return result;
    }

    public T recuperarPorID(int id) {
        String queryOne = "SELECT * FROM " + getNomeTabela() + " where " + getNomeColunaPrimaryKey() + " = " + id;
        List<T> result = recuperarPorQuery(queryOne);
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public List<T> recuperarPorQuery(String query) {

        Cursor cursor = dataBase.rawQuery(query, null);

        List<T> result = new ArrayList<T>();
        if (cursor.moveToFirst()) {
            do {
                ContentValues contentValues = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                T t = contentValuesParaEntidade(contentValues);
                result.add(t);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public void fecharConexao() {
        if (dataBase != null && dataBase.isOpen())
            dataBase.close();
    }
}