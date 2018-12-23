/*
 * Copyright (c) 1998-2017 by Richard A. Wilkes. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, version 2.0. If a copy of the MPL was not distributed with
 * this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * This Source Code Form is "Incompatible With Secondary Licenses", as
 * defined by the Mozilla Public License, version 2.0.
 */

package com.trollworks.toolkit.io.conduit;

import com.trollworks.toolkit.io.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class Client extends Thread {
    private Server           mServer;
    private Socket           mClientSocket;
    private DataInputStream  mClientInput;
    private DataOutputStream mClientOutput;

    /**
     * Creates a new client processor for the server.
     *
     * @param server The owning server.
     * @param socket The socket containing the client connection.
     * @throws IOException if the socket's i/o streams cannot be retrieved.
     */
    Client(Server server, Socket socket) throws IOException {
        super(Conduit.class.getSimpleName() + '$' + Client.class.getSimpleName() + '#' + server.getNextClientCounter() + '@' + server.getServerSocket().getLocalSocketAddress());
        setPriority(NORM_PRIORITY);
        setDaemon(true);
        mServer       = server;
        mClientSocket = socket;
        mClientInput  = new DataInputStream(socket.getInputStream());
        mClientOutput = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                mServer.send(new ConduitMessage(mClientInput));
            }
        } catch (Exception exception) {
            // An exception here can be ignored, as its just the connection
            // going away, which we deal with later.
        }

        shutdown();
    }

    /**
     * Sends a message to the client.
     *
     * @param msg The message.
     */
    void send(ConduitMessage msg) {
        try {
            msg.send(mClientOutput);
        } catch (Exception exception) {
            shutdown();
        }
    }

    /** Shuts down this client processor. */
    synchronized void shutdown() {
        try {
            mClientSocket.close();
        } catch (Exception exception) {
            Log.error(exception);
        }
        mServer.remove(this);
    }
}
