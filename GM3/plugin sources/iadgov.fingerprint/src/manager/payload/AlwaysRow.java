package iadgov.fingerprint.manager.payload;


import core.fingerprint3.Fingerprint;
import core.fingerprint3.Return;
import iadgov.fingerprint.manager.tree.PayloadItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;

import java.io.Serializable;
import java.util.List;

public class AlwaysRow extends OpRow {
    ObservableList<PayloadItem.OpType> availableOps;

    public AlwaysRow() {
        this(null);
    }

    public AlwaysRow(List<ReturnRow> returns) {
        super(PayloadItem.OpType.ALWAYS);

        availableOps = FXCollections.observableArrayList();
        availableOps.addAll(PayloadItem.OpType.RETURN);

        if (returns != null) {
            this.getChildren().addAll(returns);
        } else {
            this.getChildren().add(new ReturnRow());
        }
    }

    @Override
    public HBox getInput() {
        return new HBox();
    }

    @Override
    public ObservableList<PayloadItem.OpType> getAvailableOps() {
        return this.availableOps;
    }

    @Override
    public Serializable getOperation() {
        Fingerprint.Payload.Always always = factory.createFingerprintPayloadAlways();
        this.getChildren().forEach(child -> {
            if (child.getOperation() instanceof Return) {
                always.getReturn().add((Return) child.getOperation());
            }
        });

        return always;
    }

}